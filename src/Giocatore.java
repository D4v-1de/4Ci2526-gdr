import java.util.ArrayList;

public abstract class Giocatore {
    
    protected String nome;
    protected int hp;
    protected int mana;
    protected int peso;
    protected final int HP_MAX, MANA_MAX, PESO_MAX;
    protected Razza razza;
    protected ArrayList<Equip> inventario;

    public Giocatore(String nome, int hp, int mana, int peso, Razza razza) {
        this.nome = nome;
        this.hp = this.HP_MAX = hp;
        this.mana = this.MANA_MAX = mana;
        this.peso = 0;
        this.PESO_MAX = peso;
        this.razza = razza;

        this.inventario = new ArrayList<>();
    }

    public int attaccaMischia(Giocatore target, int danno) {

        boolean haArma = false;

        // cerco nell'inventario se ho almeno un'arma da mischia
        for (Equip e : inventario) {

            if (e.getTipo() == TipoEquip.ArmaMelee) {
                haArma = true;
                break;
            }
        }

        // controllo l'istanza del mio oggetto e attacco solo se ho l'arma
        if (!(this instanceof Guerriero) || !haArma) {
            return 0;
        }

        // verifico se il target ha armature e le conto
        int armature = 0;
        for (Equip e : target.getInventario()) {
            
            if (e.getTipo() == TipoEquip.Armatura)
                armature++;
        }

        // modifico i puntivita del target
        int dannoFinale = danno / (armature + 1);
        target.setHp(target.getHp() - dannoFinale); 
        return dannoFinale;
    }

    public int attaccaRanged(Giocatore target, int danno) {

        boolean haArma = false;

        // cerco nell'inventario se ho almeno un'arma ranged
        for (Equip e : inventario) {

            if (e.getTipo() == TipoEquip.ArmaRanged) {
                haArma = true;
                break;
            }
        }

        // controllo l'istanza del mio oggetto e attacco solo se ho l'arma
        if (!(this instanceof Arciere) || !haArma) {
            return 0;
        }

        // verifico se il target ha armature e le conto
        int armature = 0;
        for (Equip e : target.getInventario()) {
            
            if (e.getTipo() == TipoEquip.Armatura)
                armature++;
        }

        // modifico i puntivita del target
        int dannoFinale = danno / (armature + 1);
        target.setHp(target.getHp() - dannoFinale); 
        return dannoFinale;
    }

    // calcola il peso attuale portato dal giocatore
    private void aggiornaPeso() {

        peso = 0;
        for (Equip e : inventario) {
            peso += e.getPeso();
        }
    }

    // elimina tutti gli elementi dall'inventario
    public void svuotaInventario() {
        inventario.clear();
        aggiornaPeso();
    }

    // aggiungo un item all'inventario
    public boolean aggiungiEquip(Equip nuovo) {

        if (nuovo.getPeso() + this.peso > PESO_MAX) return false; 

        inventario.add(nuovo);
        aggiornaPeso();

        return true;
    }

    public boolean isMorto() {

        return hp <= 0;
    }

    public void saluta() {
        System.out.println("Ciao, mi chiamo " + nome + " e sono un " + razza);
    }

    // controlla se il peso attuale supera il carico massimo
    public boolean isTroppoCarico() {
        return peso > PESO_MAX;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {

        if (hp >= HP_MAX)
            this.hp = HP_MAX;
        else
            this.hp = hp;
    }

    public int getMana() {
        return mana;
    }

    public int getPeso() {
        return peso;
    }

    public ArrayList<Equip> getInventario() {
        return inventario;
    }

    
}
