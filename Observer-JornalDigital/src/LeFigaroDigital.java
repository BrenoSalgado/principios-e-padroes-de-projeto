import java.util.ArrayList;
import java.util.List;

public class LeFigaroDigital implements JornalDigital {
    private List<AssinanteApp> assinantes;
    private String noticia;

    public LeFigaroDigital() {
        this.assinantes = new ArrayList<>();
    }


    public void inscrever(AssinanteApp a) {
        assinantes.add(a);
    }


    public void cancelar(AssinanteApp a) {
        assinantes.remove(a);
    }


    public void notificar() {
        for (AssinanteApp assinante : assinantes) {
            assinante.atualizar();
        }
    }


    public String getNoticia() {
        return this.noticia;
    }

    public void novaNoticia(String noticia) {
        this.noticia = noticia;
        notificar();
    }
}