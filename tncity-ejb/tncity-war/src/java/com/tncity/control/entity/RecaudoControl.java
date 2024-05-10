/**
 * @name Recaudo Facade
 *
 * @ description crud de recaudo
 *
 * @author Guiovanny Anatoli Caro Daza [guiovanny.caro@tncolombia.com.co]
 * @copyright TN Colombia SAS
 * @module Back Recargas Inpec,
 * @version 1.14.0
 * @date 11 de agosto de 2020
 */
package com.tncity.control.entity;

import com.tncity.control.general.AbstractControl;
import com.tncity.facade.entity.BenefactorFacade;
import com.tncity.facade.entity.BeneficiarioFacade;
import com.tncity.facade.entity.PasarelaPagoFacade;
import com.tncity.jpa.graphic.Recaudos.PieRecaudos;
import com.tncity.facade.entity.RecaudoFacade;
import com.tncity.facade.entity.TransaccionesFacade;
import com.tncity.facade.entity.UsuarioFacade;
import com.tncity.facade.general.AbstractFacade;
import com.tncity.jpa.pojo.Pasarelapago;
import com.tncity.jpa.pojo.Recaudo;
import com.tncity.jpa.pojo.Transacciones;
import com.tncity.jpa.pojo.Usuario;
import com.tncity.jpa.pojoaux.TelefoniaJson;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import org.primefaces.model.chart.PieChartModel;

@ManagedBean(name = "recaudoControl")
@RequestScoped
public class RecaudoControl extends AbstractControl<Recaudo> {

    @EJB
    RecaudoFacade facadeRecaudo;

    @EJB
    TransaccionesFacade facadetransaccion;

    @EJB
    PasarelaPagoFacade facadePasarelaPago;

    @EJB
    BeneficiarioFacade facade;

    @EJB
    BenefactorFacade facadebenefactor;

    @EJB
    PasarelaPagoFacade facadepasarelapago;

    @EJB
    UsuarioFacade facadeuser;
    String ciudad;
    String idpasarela;

    String comision;

     List<Recaudo> listrecaudotrans = new ArrayList<>();
     
    public List<Recaudo> getListrecaudotrans() {
        return listrecaudotrans;
    }

    public void setListrecaudotrans(List<Recaudo> listrecaudotrans) {
        this.listrecaudotrans = listrecaudotrans;
    }
    List<Pasarelapago> lispasarelapago = new ArrayList<>();

    List<Recaudo> lisrecaudos = new ArrayList<>();
    private PieChartModel tortaRecaudos = new PieChartModel();

    public List<Recaudo> getLisrecaudos() {
        return lisrecaudos;
    }

    public void setLisrecaudos(List<Recaudo> lisrecaudos) {
        this.lisrecaudos = lisrecaudos;
    }

    public PieChartModel getTortaRecaudos() {
        return tortaRecaudos;
    }

    public void setTortaRecaudos(PieChartModel tortaRecaudos) {
        this.tortaRecaudos = tortaRecaudos;
    }

    public List<Pasarelapago> getLispasarelapago() {
        return lispasarelapago;
    }

    public void setLispasarelapago(List<Pasarelapago> lispasarelapago) {
        this.lispasarelapago = lispasarelapago;
    }

    public String getComision() {
        return comision;
    }

    public void setComision(String comision) {
        this.comision = comision;
    }

    public String getIdpasarela() {
        return idpasarela;
    }

    public void setIdpasarela(String idpasarela) {
        this.idpasarela = idpasarela;
    }

    List<Recaudo> lisben = new ArrayList<>();
    List<Recaudo> lisbenfactor = new ArrayList<>();

    List<Transacciones> listtransaccion = new ArrayList<>();
    List<Pasarelapago> listpasarelas = new ArrayList<>();

    public List<Pasarelapago> getListpasarelas() {
        return listpasarelas;
    }

    public void setListpasarelas(List<Pasarelapago> listpasarelas) {
        this.listpasarelas = listpasarelas;
    }

    public List<Transacciones> getListtransaccion() {
        return listtransaccion;
    }

    public void setListtransaccion(List<Transacciones> listtransaccion) {
        this.listtransaccion = listtransaccion;
    }

    public List<Recaudo> getLisbenfactor() {
        return lisbenfactor;
    }

    public void setLisbenfactor(List<Recaudo> lisbenfactor) {
        this.lisbenfactor = lisbenfactor;
    }

    public List<Recaudo> getLisben() {
        return lisben;
    }

    public void setLisben(List<Recaudo> lisben) {
        this.lisben = lisben;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }
    String redirigir;

    Recaudo rec;

    public Recaudo getRec() {
        return rec;
    }

    public void setRec(Recaudo rec) {
        this.rec = rec;
    }

    String otromonto;
    String redir;
    Integer pin;
    boolean aceptaCondiciones = false;
    String telefono;

    public String getRedirigir() {
        return redirigir;
    }

    public void setRedirigir(String redirigir) {
        this.redirigir = redirigir;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    String valor;

    public String getOtromonto() {
        return otromonto;
    }

    public void setOtromonto(String otromonto) {
        this.otromonto = otromonto;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    TelefoniaJson pinData = new TelefoniaJson();

    List<Recaudo> lstrecaudo = new ArrayList<>();

    public List<Recaudo> getLstrecaudo() {
        return lstrecaudo;
    }

    public void setLstrecaudo(List<Recaudo> lstrecaudo) {
        this.lstrecaudo = lstrecaudo;
    }

    public String getRedir() {
        return redir;
    }

    public TelefoniaJson getPinData() {
        return pinData;
    }

    public void setPinData(TelefoniaJson pinData) {
        this.pinData = pinData;
    }

    public void setRedir(String redir) {
        this.redir = redir;
    }

    public Integer getPin() {
        return pin;
    }

    public void setPin(Integer pin) {
        this.pin = pin;
    }

    List<Recaudo> lstRecaudos = new ArrayList<>();

    public List<Recaudo> getLstRecaudos() {
        return lstRecaudos;
    }

    public void setLstRecaudos(List<Recaudo> lstRecaudos) {
        this.lstRecaudos = lstRecaudos;
    }

    public RecaudoControl() {
        super(Recaudo.class);
        attrOrd = "idrecaudo";
        ascDesc = "ASC";
    }

    @Override
    protected AbstractFacade getFacade() {
        return facadeRecaudo;
    }

    @Override
    protected String displayObj(Recaudo obj) {
        return obj.getIdrecaudo() + " - " + obj.getValor() + " - " + obj.getIdbeneficiario()
                + " - " + obj.getIdbenefactor() + " - " + obj.getCreatedAt() + " - " + obj.getCheckTelefoniaAt()
                + " - " + obj.getLog() + " - " + obj.getEstado();
    }

    //recuperar recaudo por numero recaudo
    public void recuperaByNumRecaudo(Integer idrecaudo) {
        obj = facadeRecaudo.findByNumRecaudo(idrecaudo);
    }

    //nuevo recaudo
    public void nuevo() {
        StringBuilder err = new StringBuilder();
        facadeRecaudo.create(obj, err);
        if (err.toString().isEmpty()) {
            facesUtil.msgOk("", "Registro realizado");
            successful = true;
        } else {
            facesUtil.msgError("", err.toString());
            successful = false;
        }
    }

    //editar recaudo
    public void editar() {
        StringBuilder err = new StringBuilder();
        facadeRecaudo.edit(obj, err);
        if (err.toString().isEmpty()) {
            facesUtil.msgOk("", "Registro Editado");
            successful = true;
        } else {
            facesUtil.msgError("", err.toString());
            successful = false;
        }
    }

    //eliminar recaudo
    public void eliminar() {
        StringBuilder err = new StringBuilder();
        facadeRecaudo.delete(obj, err);
        if (err.toString().isEmpty()) {
            facesUtil.msgOk("", "Registro ELiminado");
            successful = true;
        } else {
            facesUtil.msgError("", err.toString());
            successful = false;
        }

    }

    //recuperar conteo total de recaudo
    public void recuperaCountTotalRecaudo() {

        Recaudo r = new Recaudo();
        r = facadeRecaudo.listByRecaudo();

        lstRecaudos.add(r);
    }

    // recuperar conteo del numero total de recaudo
    public void recuperaNumTotalReg() {
        if (valBusq != null && !valBusq.trim().isEmpty()) {
            totalConsulta = facadeRecaudo.countBuscar(valBusq);
        }
    }

    //buscar recaudo por valor de busqueda
    public void buscarFullText() {
        int first = facesUtil.getFacesParamInteger("pag_") * maxRegLista;
        lst = facadeRecaudo.buscarFullText(valBusq, first, maxRegLista);
    }

    public String confirmacion() {
        return "recaudo4";
    }

    //recaudo pagos inteligentes
    public void recaudoinpec(Long id, String tdoc, String numdoc, String telrec, String valor, String comision) {

        System.out.println("datos recarga " + id + " " + tdoc + " " + numdoc + " " + telrec + " " + valor + " " + comision);

        StringBuilder err = new StringBuilder();

        String ip = facesUtil.getHostClient();

        facadeRecaudo.GuardarRecargarInpec(id, tdoc, numdoc, telrec, valor, ciudad, ip, comision);
        if (err.toString().isEmpty()) {
            successful = true;
            facesUtil.msgOk("Enviando Transaccion", "");
        } else {
            successful = false;
            facesUtil.msgError("ALERTA: ", err.toString());
        }
    }

    //obtener recaudo por id recaudo
    public String ObtenerRecaudoById(Long id, String tdoc, String numdoc, String telrec, String valor) {

        StringBuilder err = new StringBuilder();

        rec = facadeRecaudo.ObtenerRecaudoById(id, tdoc, numdoc, telrec, valor);
        rec.getIdrecaudo();

        String idrecaudo = "" + rec.getIdrecaudo();
        return idrecaudo;
    }

    //validar recaudo inpec
    public void validarinpec(Long id, String tdoc, String numdoc, String telrec, String valor) {
        StringBuilder err = new StringBuilder();

        totalConsulta = facadetransaccion.totalDetallesPago(id, tdoc, numdoc, telrec, valor);
        if (totalConsulta > 0) {
            successful = true;
            facesUtil.msgOk("Transaccion no terminada", "");
        } else {
            successful = false;
            facesUtil.msgError("ALERTA: ", "Transaccion terminada.");
        }
    }

    //validar subdistribuidor recaudo por id 
    public void validarsubdistribuidor(Long idrecaudo) {
        StringBuilder err = new StringBuilder();

        totalConsulta = facadetransaccion.totalDetallesPagoSubdistribuidor(idrecaudo);
        if (totalConsulta > 0) {
            successful = true;
            facesUtil.msgOk("Transaccion no terminada", "");
        } else {
            successful = false;
            facesUtil.msgError("ALERTA: ", "Transaccion terminada.");
        }
    }

    //mostrar detalles recaudo por id recaudo
    public void mostrarDetallesRecaudo(Long idrecaudo) {

        listtransaccion = facadetransaccion.mostrarDetallesPago(idrecaudo);

    }

//confirmar recaudo
    public void ConfirmarRecaudo() {

        long idrecaudo = (long) facesUtil.getFacesParamInteger("idrecaudo_");

        lstrecaudo = facadeRecaudo.Recaudoconfirmacion(idrecaudo);

    }

    //eliminar recaudo
    public void eliminarrecaudo(int i) {
        Integer idrecaudo = facesUtil.getFacesParamInteger("idrecaudo_");
        System.out.println("" + i);
        // lstrecaudo = facadeRecaudo.listByRecaudo(idrecaudo);
    }

    public double parseDouble(String v) {
        return Double.parseDouble(v);
    }

    public void addMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    //abortar recaudo por id recaudo
    public void recaudoabortar(Long idrecaudo) {

        facadeRecaudo.abortarRecaudo(idrecaudo);

    }

    public String codificar(String cadena) {

        return facadeRecaudo.codificar(cadena);
    }

    public String decodificar(String cadena) {

        return facadeRecaudo.decodificar(cadena);
    }

//enviar pago recaudo
    public String enviarapagos(String id, String tdoc, String numdoc, String telrec, String valor) {

        String url = "";
        url = facadeRecaudo.ValidarRecargaInpec(id,
                tdoc,
                numdoc,
                telrec,
                valor
        );

        return url;
    }

//validar conexion recaudo
    public void validarConexion() {

        String conexion = facadeRecaudo.getConnectionStatus();
        System.out.println("conect" + conexion);
        if (conexion.equals("Online")) {
            redirigir = "true";
        } else {
            redirigir = "false";
        }

    }

    //validar beneficiario recaudo por id recaudo
    public void validarbeneficiario(Long idrecaudo) {
        StringBuilder err = new StringBuilder();
        System.out.println("validar retorno recaudo " + idrecaudo);
        totalConsulta = facadetransaccion.totalDetallesPagoSubdistribuidor(idrecaudo);
        if (totalConsulta > 0) {
            successful = true;
            facesUtil.msgOk("Transaccion no terminada", "");
        } else {
            successful = false;
            facesUtil.msgError("ALERTA: ", "Transaccion terminada.");
        }
    }

    //validar beneficiario por id recaudo
    public void validarbeneficiariodos(Long idrecaudo) {
        StringBuilder err = new StringBuilder();
        System.out.println("validar retorno recaudo " + idrecaudo);
        totalConsulta = facadetransaccion.DetallesPagoSubdistribuidor(idrecaudo);
        if (totalConsulta > 0) {
            successful = true;
            facesUtil.msgOk("Transaccion no terminada", "");
        } else {
            successful = false;
            facesUtil.msgError("ALERTA: ", "Transaccion terminada.");
        }
    }

    //validar tipo benefactor por id recaudo
    public void validartipobenefactor(Long idrecaudo) {
        StringBuilder err = new StringBuilder();

        totalConsulta = facadetransaccion.validartipobenefactor(idrecaudo);
        if (totalConsulta > 0) {
            successful = true;
            facesUtil.msgOk("Transaccion no terminada", "");
        } else {
            successful = false;
            facesUtil.msgError("ALERTA: ", "Transaccion terminada.");
        }
    }

    //listado recaudos
    public List<SelectItem> listadorecaudo() {

        Integer idinforme = facesUtil.getFacesParamInteger("idinforme_");
        List<SelectItem> lstI = new ArrayList<>();
        List<Recaudo> lstT = facadeRecaudo.ListRecaudos();

        lstI.add(new SelectItem(null, "Seleccione una opcion..."));
        for (Recaudo b : lstT) {
            SelectItem it = new SelectItem(b.getIdbenefactor(), "" + b.getIdbenefactor().getIdpersona().getNombres());
            lstI.add(it);
        }
        return lstI;

    }

    //recuperar benefactorores por id pasarela
    public void RecuperaBenefactoresId(Integer idpasarela) {

        lisbenfactor = facadeRecaudo.BenefactorIDPasarela(idpasarela, "idrecaudo", "DESC");
    }
    
  

    //recuperar numero total de recaudos por id benefactor
    public void recuperaNumTotalRecaudos(Integer idbenefactor) {

        totalConsulta = facadeRecaudo.countBuscarRecaudoId(idbenefactor);
    }

    // recuperar conteo total de recaudos por beneficiario
    public void recuperaCountTotalBenRecaudosId(Integer idbenefactor) {

        System.out.println("listado recaudos benefactor " + idbenefactor);
        lisben = facadeRecaudo.RecaudoIDBenefactor(idbenefactor, "idbeneficiario", "ASC");
    }

    //recuperar recaudos por id recaudo
    public void recuperaRecaudosId(String idrecaudo) {

        lisbenfactor = facadeRecaudo.findrecaudobyid(idrecaudo);

    }

//listado pasarelas
    public void listadopasarelas() {

        Integer pasarela = facadeRecaudo.getPasarelaDia();

        listpasarelas = facadePasarelaPago.PasarelasPagoID();
    }

    //recuparar recaudo pasarela dia
    public void recuperaPasarelaDia() {

        Integer pasarela = facadeRecaudo.getPasarelaDia();

        listpasarelas = facadePasarelaPago.PasarelasPago();
        System.out.println("conect" + pasarela);

        for (Pasarelapago listpasarelas : facadePasarelaPago.PasarelasPago()) {

            System.out.println("redirigir a una opcion :" + listpasarelas.getIdpasarela() + "\\");
            if (listpasarelas.getIdpasarela().equals(pasarela)) {
                redirigir = listpasarelas.getNombre();
                System.out.println("redirigir a una opcion" + redirigir);
            }

        }

    }

    //pasarela recaudo al dia
    public Integer pasareladia() {

        Integer pasarela = facadeRecaudo.getPasarelaDia();
        System.out.println("conect" + pasarela);
        return pasarela;

    }
//obtener comision recaudo

    public String obtenercomision() {

        facadeRecaudo.getComisionPasarela();

        return facadeRecaudo.getComisionPasarela();
    }
    // recaudos pasarelas pago

    public void RecaudoPasarelasPago(Long id, String tdoc, String numdoc, String telrec, String valor, String comision) {

        System.out.println("datos recarga " + id + " " + tdoc + " " + numdoc + " " + telrec + " " + valor + " " + comision);

        StringBuilder err = new StringBuilder();

        String ip = facesUtil.getHostClient();

        facadeRecaudo.RecargarPasarela(id, tdoc, numdoc, telrec, valor, ciudad, ip, comision);
        if (err.toString().isEmpty()) {
            successful = true;
            facesUtil.msgOk("Enviando Transaccion", "");
        } else {
            successful = false;
            facesUtil.msgError("ALERTA: ", err.toString());
        }
    }

//envio pagos recaudos pasarela pago payzend 
    public String enviarpagopayzend(String id, String tdoc, String numdoc, String telrec, String valor) {

        String url = "";
        url = facadeRecaudo.RecargaPayzend(id,
                tdoc,
                numdoc,
                telrec,
                valor
        );

        return url;
    }

    //recuperar recaudo pasarela por orden de id
    public void RecuperaPasarelasId() {

        lispasarelapago = facadepasarelapago.PasarelasIDPasarela("idpasarela", "DESC");
    }

    //total recaudos por fecha
    public void TotalRecaudosFechas(String desde, String hasta, String idbenefactor, String idpasarela) {
        System.out.println("datos informe " + desde + hasta + idbenefactor + idpasarela);
        totalConsulta = facadeRecaudo.countTotalRecaudosFechas(desde, hasta, idbenefactor, idpasarela);

    }

//listar recaudos
    public void listarRecaudos(String desde, String hasta, String idbenefactor, String idpasarela) {

        crearGraficaRecaudos(desde, hasta, idbenefactor, idpasarela);
    }

// crear grafico recaudos
    public void crearGraficaRecaudos(String desde, String hasta, String idbenefactor, String idpasarela) {
        List<PieRecaudos> lsthab = facadeRecaudo.listrecaudosBy(desde, hasta, idbenefactor, idpasarela);

        for (PieRecaudos ob : lsthab) {
            tortaRecaudos.set(ob.getEstado() + " (" + ob.getRecaudos() + ")", ob.getRecaudos());
        }

        tortaRecaudos.setTitle("Recaudo Familiar");
        tortaRecaudos.setLegendPosition("w");
        tortaRecaudos.setFill(true);
        tortaRecaudos.setShowDataLabels(true);
        tortaRecaudos.setShadow(true);
        tortaRecaudos.setDiameter(150);

    }

    //listar todos los recaudos
    public void listarRecaudosCompleta(String desde, String hasta) {

        crearGraficaRecaudosCompleta(desde, hasta);
    }

    //crear grafica recaudos conpleta
    public void crearGraficaRecaudosCompleta(String desde, String hasta) {
        List<PieRecaudos> lsthab = facadeRecaudo.listRecaudosCompleta(desde, hasta);

        for (PieRecaudos ob : lsthab) {
            tortaRecaudos.set(ob.getEstado() + " (" + ob.getRecaudos() + ")", ob.getRecaudos());
        }

        tortaRecaudos.setTitle("Recaudo Completo");
        tortaRecaudos.setLegendPosition("w");
        tortaRecaudos.setFill(true);
        tortaRecaudos.setShowDataLabels(true);
        tortaRecaudos.setShadow(true);
        tortaRecaudos.setDiameter(150);

    }

    //recaudos web services
    public void TotalRecaudosFechasWebServices(String desde, String hasta) {
        System.out.println("datos informe " + desde + hasta);
        totalConsulta = facadeRecaudo.countTotalRecaudosFechasWebServices(desde, hasta);

    }

//listar recaudos web services
    public void listarRecaudosWebServices(String desde, String hasta) {

        crearGraficaRecaudosWebServices(desde, hasta);
    }

    //crear grafico recaudos web services
    public void crearGraficaRecaudosWebServices(String desde, String hasta) {
        List<PieRecaudos> lsthab = facadeRecaudo.listrecaudosByWS(desde, hasta);

        for (PieRecaudos ob : lsthab) {
            tortaRecaudos.set(ob.getEstado() + " (" + ob.getRecaudos() + ")", ob.getRecaudos());
        }

        tortaRecaudos.setTitle("Recaudo Fechas");
        tortaRecaudos.setLegendPosition("w");
        tortaRecaudos.setFill(true);
        tortaRecaudos.setShowDataLabels(true);
        tortaRecaudos.setShadow(true);
        tortaRecaudos.setDiameter(150);

    }

    //lista recaudos completo web services
    public void listarRecaudosCompletaWebServices() {

        crearGraficaRecaudosCompletaWebServices();
    }

    //crear frafico recaudo completa web services
    public void crearGraficaRecaudosCompletaWebServices() {
        List<PieRecaudos> lsthab = facadeRecaudo.listRecaudosCompletaWS();

        for (PieRecaudos ob : lsthab) {
            tortaRecaudos.set(ob.getEstado() + " (" + ob.getRecaudos() + ")", ob.getRecaudos());
        }

        tortaRecaudos.setTitle("Recaudo Completo");
        tortaRecaudos.setLegendPosition("w");
        tortaRecaudos.setFill(true);
        tortaRecaudos.setShowDataLabels(true);
        tortaRecaudos.setShadow(true);
        tortaRecaudos.setDiameter(150);

    }

    public void RecuperarTodosRecaudos() {
        listrecaudotrans =facadeRecaudo.TodosRecaudoTrans("idrecaudo", "DESC");
    }

}
