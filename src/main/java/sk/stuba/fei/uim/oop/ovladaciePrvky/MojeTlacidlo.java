package sk.stuba.fei.uim.oop.ovladaciePrvky;

import sk.stuba.fei.uim.oop.grafika.GrafikaBludiska;

import javax.swing.*;
import java.awt.event.ActionListener;

/**
 * Abstraktna trieda tlacitka, z ktorej dedia jednotlive tlacitka na pohyb hore/dole/vlavo/vpravo
 * Akcia vsetkych tlacitok ma rovnaku logiku len s upravou vypoctu novej pozicie (podla toho akym smerom sa ma veza pohnut)
 * Vo vseobecnosti tlacitko funguje tak, ze skontroluje ci policko na ktorom sa veza aktualne nachadza nema hranu v tom
 * smere v ktorom sa chce hrac pohnut (ci nechce prechadzat cez hrany, co sa nemoze). Ak v danom smere hrana nie je,
 * urci policko, na ktore sa veza posunie. To sa urci z poradoveho cisla policka a smeru v ktorom sa chce veza pohnut -
 * pohyb hore => pohyb o riadok vyssie => poradie policka - rozmer bludiska
 * pohyb dole => pohyb o riadok nizsie => poradie policka + rozmer bludiska
 * pohyb vpravo => pohyb o stlpec viac => poradie policka +1
 * pohyb vlavo => o stlpec menej => poradie policka -1
 * Ulozi sa povodna pozicia veze, vezi sa nastavi nova pozicia a cela plocha sa prekresli
 */

public abstract class MojeTlacidlo extends JButton implements ActionListener {
    protected GrafikaBludiska g;
}
