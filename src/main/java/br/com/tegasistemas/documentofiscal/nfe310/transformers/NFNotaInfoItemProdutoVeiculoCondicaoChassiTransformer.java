package br.com.tegasistemas.documentofiscal.nfe310.transformers;

import br.com.tegasistemas.documentofiscal.nfe310.classes.NFNotaInfoItemProdutoVeiculoCondicaoChassi;
import org.simpleframework.xml.transform.Transform;

public class NFNotaInfoItemProdutoVeiculoCondicaoChassiTransformer implements Transform<NFNotaInfoItemProdutoVeiculoCondicaoChassi> {

    @Override
    public NFNotaInfoItemProdutoVeiculoCondicaoChassi read(final String codigo) {
        return NFNotaInfoItemProdutoVeiculoCondicaoChassi.valueOfCodigo(codigo);
    }

    @Override
    public String write(final NFNotaInfoItemProdutoVeiculoCondicaoChassi tipo) {
        return tipo.getCodigo();
    }
}