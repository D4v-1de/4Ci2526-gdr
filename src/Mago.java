public class Mago extends Giocatore {

    private final int MANA_ATT = 20;

    public Mago(String nome, int hp, int mana, int peso, Razza razza) {
        super(nome, hp, mana, peso, razza);
    }

    @Override
    public int attacca(Giocatore target, int danno) {

        int armature = 0;
        for (Equip e : target.getInventario()) {

            if (e.getTipo() == TipoEquip.Armatura)
                armature++;
        }
        // dobbiamo verificare se abbiamo abbastanza mana e aggiornare
        if (mana - MANA_ATT < 0) {
            mana -= MANA_ATT;

            int multi = 1;
            if (target instanceof Guerriero || target instanceof Arciere) {
                multi = 2;
            }
            int dannoFinale = multi * danno / (armature + 1);
            target.setHp(target.getHp() - dannoFinale);

            if (target.isMorto()) {
                ricaricaMana();
            }

            return dannoFinale;
        }
        return 0;
    }

    public void ricaricaMana() {
        mana = MANA_MAX;
    }
}