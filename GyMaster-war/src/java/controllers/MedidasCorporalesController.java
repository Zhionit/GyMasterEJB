package controllers;

import entities.MedidasCorporales;
import controllers.util.JsfUtil;
import controllers.util.PaginationHelper;
import sessionBeans.MedidasCorporalesFacade;

import java.io.Serializable;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;

@Named("medidasCorporalesController")
@SessionScoped
public class MedidasCorporalesController implements Serializable {

    private MedidasCorporales current;
    private DataModel items = null;
    @EJB
    private sessionBeans.MedidasCorporalesFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;

    public MedidasCorporalesController() {
    }

    public MedidasCorporales getSelected() {
        if (current == null) {
            current = new MedidasCorporales();
            current.setMedidasCorporalesPK(new entities.MedidasCorporalesPK());
            selectedItemIndex = -1;
        }
        return current;
    }

    private MedidasCorporalesFacade getFacade() {
        return ejbFacade;
    }

    public PaginationHelper getPagination() {
        if (pagination == null) {
            pagination = new PaginationHelper(10) {

                @Override
                public int getItemsCount() {
                    return getFacade().count();
                }

                @Override
                public DataModel createPageDataModel() {
                    return new ListDataModel(getFacade().findRange(new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}));
                }
            };
        }
        return pagination;
    }

    public String prepareList() {
        recreateModel();
        return "List";
    }

    public String prepareView() {
        current = (MedidasCorporales) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new MedidasCorporales();
        current.setMedidasCorporalesPK(new entities.MedidasCorporalesPK());
        selectedItemIndex = -1;
        return "Create";
    }

    public String create() {
        try {
            current.getMedidasCorporalesPK().setCliente(current.getCliente1().getId());
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("MedidasCorporalesCreated"));
            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        current = (MedidasCorporales) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            current.getMedidasCorporalesPK().setCliente(current.getCliente1().getId());
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("MedidasCorporalesUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (MedidasCorporales) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        performDestroy();
        recreatePagination();
        recreateModel();
        return "List";
    }

    public String destroyAndView() {
        performDestroy();
        recreateModel();
        updateCurrentItem();
        if (selectedItemIndex >= 0) {
            return "View";
        } else {
            // all items were removed - go back to list
            recreateModel();
            return "List";
        }
    }

    private void performDestroy() {
        try {
            getFacade().remove(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("MedidasCorporalesDeleted"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
        }
    }

    private void updateCurrentItem() {
        int count = getFacade().count();
        if (selectedItemIndex >= count) {
            // selected index cannot be bigger than number of items:
            selectedItemIndex = count - 1;
            // go to previous page if last page disappeared:
            if (pagination.getPageFirstItem() >= count) {
                pagination.previousPage();
            }
        }
        if (selectedItemIndex >= 0) {
            current = getFacade().findRange(new int[]{selectedItemIndex, selectedItemIndex + 1}).get(0);
        }
    }

    public DataModel getItems() {
        if (items == null) {
            items = getPagination().createPageDataModel();
        }
        return items;
    }

    private void recreateModel() {
        items = null;
    }

    private void recreatePagination() {
        pagination = null;
    }

    public String next() {
        getPagination().nextPage();
        recreateModel();
        return "List";
    }

    public String previous() {
        getPagination().previousPage();
        recreateModel();
        return "List";
    }

    public SelectItem[] getItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), false);
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), true);
    }

    public MedidasCorporales getMedidasCorporales(entities.MedidasCorporalesPK id) {
        return ejbFacade.find(id);
    }

    @FacesConverter(forClass = MedidasCorporales.class)
    public static class MedidasCorporalesControllerConverter implements Converter {

        private static final String SEPARATOR = "#";
        private static final String SEPARATOR_ESCAPED = "\\#";

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            MedidasCorporalesController controller = (MedidasCorporalesController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "medidasCorporalesController");
            return controller.getMedidasCorporales(getKey(value));
        }

        entities.MedidasCorporalesPK getKey(String value) {
            entities.MedidasCorporalesPK key;
            String values[] = value.split(SEPARATOR_ESCAPED);
            key = new entities.MedidasCorporalesPK();
            key.setCliente(values[0]);
            key.setFechaRegistro(java.sql.Date.valueOf(values[1]));
            return key;
        }

        String getStringKey(entities.MedidasCorporalesPK value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value.getCliente());
            sb.append(SEPARATOR);
            sb.append(value.getFechaRegistro());
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof MedidasCorporales) {
                MedidasCorporales o = (MedidasCorporales) object;
                return getStringKey(o.getMedidasCorporalesPK());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + MedidasCorporales.class.getName());
            }
        }

    }

}
