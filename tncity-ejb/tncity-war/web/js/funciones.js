function selectV1() {
    var v1 = document.getElementById("form3_:valor1").value;
    var v2 = document.getElementById("form3_:valor2").value;
    var v3 = document.getElementById("form3_:valor3").value;

    var suma1 = 0;
    var suma2 = 0;
    var suma3 = 0;
    var total = 0;
    if (isNaN(v1)) {
        v1 = 0;
    }
    if (isNaN(v2)) {
        v2 = 0;
    }
    if (isNaN(v3)) {
        v3 = 0;
    }


    if (v1 != '') {
        var valor1 = 100000;
        suma1 = valor1 * v1;
    } else {

        suma1 = 0;
    }

    if (v2 != '') {
        var valor2 = 20000;
        suma2 = valor2 * v2;
    } else {
        suma2 = 0;

    }

    if (v3 != '') {
        var valor3 = 50000;
        suma3 = valor3 * v3;
    } else {
        suma3 = 0;

    }


    var total = suma1 + suma2 + suma3;



    var valor = document.getElementById("form3_:total_").value = total;



    $("#total_").html(valor);
}


function opNo() {

    document.location.href = '#{utilControl.protocolHostPortContext}/app-familiar/admin/recaudo/recaudo1.xhtml';
}

function opSi() {
    var valor = document.getElementById("form3_:total_").value;

    document.location.href = '#{utilControl.protocolHostPortContext}/app-familiar/admin/recaudo/recaudo3.xhtml?id_=#{loginBenefactorControl.usLog.idpersona.idpersona}&amp;tdoc_=#{param.tdoc_}&amp;numdoc_=#{param.numdoc_}&amp;valor_=' + valor;
}

function enviarf() {
    document.location.href = '#{utilControl.protocolHostPortContext}/app-familiar/admin/recaudo/recaudo3_1.xhtml?id_=#{param.id_}&amp;tdoc_=#{param.tdoc_}&amp;numdoc_=#{param.numdoc_}';
}

function regresar() {
    document.location.href = '#{utilControl.protocolHostPortContext}/app-familiar/admin/recaudo/recaudo1.xhtml'
}
