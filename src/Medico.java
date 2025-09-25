public class Medico extends Giocatore {

    private final int MANA_CURA = 20;

    public Medico(String nome, int hp, int mana, int peso, Razza razza) {
        super(nome, hp, mana, peso, razza);
    }

    @Override
    public void ricaricaMana() {

        mana = MANA_MAX;

    }

    @Override
    public int attacca(Giocatore target, int cura) {

        if (target.isMorto()) {
            return 0;
        }

        if (mana - MANA_CURA < 0) {
            mana -= MANA_CURA;

            int nuovoHp = target.getHp() + cura;
            // .............controllo................se vera........se falsa
            target.setHp(nuovoHp > target.HP_MAX ? target.HP_MAX : nuovoHp);
            return cura;
        }
        return 0;
    }

    public boolean rianima(Giocatore target) {
        // se vivo non rianimo
        if (!target.isMorto())
            return false;

        target.setHp(target.HP_MAX / 4);
        return true;
    }
}
