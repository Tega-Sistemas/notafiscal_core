package br.com.tegasistemas.documentofiscal.nfe400.classes.nota;

import br.com.tegasistemas.documentofiscal.DFBase;
import br.com.tegasistemas.documentofiscal.nfe400.classes.NFNotaInfoSituacaoTributariaPIS;
import org.simpleframework.xml.Element;

public class NFNotaInfoItemImpostoPISNaoTributado extends DFBase {

    private static final long serialVersionUID = -961225710108754016L;
    
    @Element(name = "CST")
    private NFNotaInfoSituacaoTributariaPIS situacaoTributaria;

    public NFNotaInfoItemImpostoPISNaoTributado() {
        this.situacaoTributaria = null;
    }

    public NFNotaInfoSituacaoTributariaPIS getSituacaoTributaria() {
        return this.situacaoTributaria;
    }

    public void setSituacaoTributaria(final NFNotaInfoSituacaoTributariaPIS situacaoTributaria) {
        if (!NFNotaInfoSituacaoTributariaPIS.OPERACAO_TRIBUTAVEL_MONOFASICA_ALIQUOTA_ZERO.equals(situacaoTributaria)
                && !NFNotaInfoSituacaoTributariaPIS.OPERACAO_TRIBUTAVEL_ALIQUOTA_ZERO.equals(situacaoTributaria)
                && !NFNotaInfoSituacaoTributariaPIS.OPERACAO_TRIBUTAVEL_SUBSTITUICAO_TRIBUTARIA.equals(situacaoTributaria)
                && !NFNotaInfoSituacaoTributariaPIS.OPERACAO_ISENTA_CONTRIBUICAO.equals(situacaoTributaria)
                && !NFNotaInfoSituacaoTributariaPIS.OPERACAO_SEM_INCIDENCIA_CONTRIBUICAO.equals(situacaoTributaria)
                && !NFNotaInfoSituacaoTributariaPIS.OPERACAO_COM_SUSPENSAO_CONTRIBUICAO.equals(situacaoTributaria)) {
            throw new IllegalStateException("Situacao tributaria invalida no item PIS nao tributado");
        }
        this.situacaoTributaria = situacaoTributaria;
    }
}