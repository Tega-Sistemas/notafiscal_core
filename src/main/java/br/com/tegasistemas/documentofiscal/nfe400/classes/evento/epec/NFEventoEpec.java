package br.com.tegasistemas.documentofiscal.nfe400.classes.evento.epec;

import br.com.tegasistemas.documentofiscal.nfe310.classes.nota.assinatura.NFSignature;
import br.com.tegasistemas.documentofiscal.validadores.DFBigDecimalValidador;
import br.com.tegasistemas.documentofiscal.DFBase;
import br.com.tegasistemas.documentofiscal.nfe400.classes.nota.NFNota;

import java.math.BigDecimal;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.Transient;


@Root(name = "evento")
public class NFEventoEpec extends DFBase {

	@Attribute(name = "versao", required = true)
	private String versao;

	@Element(name = "infEvento", required = true)
	private NFInfoEventoEpec infoEvento;

	@Element(name = "Signature", required = false)
	private NFSignature assinatura;

	@Transient
	private NFNota nota;

	public void setVersao(final BigDecimal versao) {
		this.versao = DFBigDecimalValidador.tamanho5Com2CasasDecimais(versao, "Versao");
	}

	public NFInfoEventoEpec getInfoEvento() {
		return this.infoEvento;
	}

	public String getVersao() {
		return this.versao;
	}

	public void setVersao(String versao) {
		this.versao = versao;
	}

	public void setInfoEvento(final NFInfoEventoEpec infoEvento) {
		this.infoEvento = infoEvento;
	}

	public void setAssinatura(final NFSignature assinatura) {
		this.assinatura = assinatura;
	}

	public NFSignature getAssinatura() {
		return this.assinatura;
	}

	public NFNota getNota() {
		return nota;
	}

	public void setNota(NFNota nota) {
		this.nota = nota;
	}

}