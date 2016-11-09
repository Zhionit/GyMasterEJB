package controllers;

import entities.HistorialDePagos;
import controllers.util.JsfUtil;
import controllers.util.PaginationHelper;
import sessionBeans.HistorialDePagosFacade;

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

@Named("historialDePagosController")
@SessionScoped
public class HistorialDePagosController implements Serializable {

    private HistorialDePagos current;
    private DataModel items = null;
    @EJB
    private sessionBeans.HistorialDePagosFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;

    public HistorialDePagosController() {
    }

    public HistorialDePagos getSelected() {
        if (current == null) {
            current = new HistorialDePagos();
            current.setHistorialDePagosPK(new entities.HistorialDePagosPK());
            selectedItemIndex = -1;
        }
        return current;
    }

    private HistorialDePagosFacade getFacade() {
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
        current = (HistorialDePagos) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new HistorialDePagos();
        current.setHistorialDePagosPK(new entities.HistorialDePagosPK());
        selectedItemIndex = -1;
        return "Create";
    }

    public String create() {
        try {
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("HistorialDePagosCreated"));
            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        current = (HistorialDePagos) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("HistorialDePagosUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (HistorialDePagos) getItems().getRowData();
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
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("HistorialDePagosDeleted"));
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

    public HistorialDePagos getHistorialDePagos(entities.HistorialDePagosPK id) {
        return ejbFacade.find(id);
    }

    @FacesConverter(forClass = HistorialDePagos.class)
    public static class HistorialDePagosControllerConverter implements Converter {

        private static final String SEPARATOR = "#";
        private static final String SEPARATOR_ESCAPED = "\\#";

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            HistorialDePagosController controller = (HistorialDePagosController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "historialDePagosController");
            return controller.getHistorialDePagos(getKey(value));
        }

        entities.HistorialDePagosPK getKey(String value) {
            entities.HistorialDePagosPK key;
            String values[] = value.split(SEPARATOR_ESCAPED);
            key = new entities.HistorialDePagosPK();
            key.setCedula(Integer.parseInt(values[0]));
            key.setAnio(Integer.parseInt(values[1]));
            key.setMes(Integer.parseInt(values[2]));
            return key;
        }

        String getStringKey(entities.HistorialDePagosPK value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value.getCedula());
            sb.append(SEPARATOR);
            sb.append(value.getAnio());
            sb.append(SEPARATOR);
            sb.append(value.getMes());
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof HistorialDePagos) {
                HistorialDePagos o = (HistorialDePagos) object;
                return getStringKey(o.getHistorialDePagosPK());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + HistorialDePagos.class.getName());
            }
        }

    }

}
