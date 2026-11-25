public class AppWeb implements AssinanteApp {
    private String usuario;
    private JornalDigital jornal;
    private EstrategiaApresentacao estrategia;

    public AppWeb(String usuario, JornalDigital jornal, EstrategiaApresentacao estrategia) {
        this.usuario = usuario;
        this.jornal = jornal;
        this.estrategia = estrategia;
    }

    public void setEstrategia(EstrategiaApresentacao estrategia) {
        this.estrategia = estrategia;
    }


    public void atualizar() {
        String noticia = jornal.getNoticia();
        String texto = estrategia.apresentar(noticia);
        System.out.println(usuario + " (Web): " + texto);
    }
}