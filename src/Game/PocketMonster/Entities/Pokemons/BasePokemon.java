package Game.PocketMonster.Entities.Pokemons;

import Game.PocketMonster.Entities.BaseEntity;
import Main.Handler;

import java.awt.image.BufferedImage;

/**
 * Created by AlexVR on 3/11/2020
 */
public class BasePokemon extends BaseEntity {
    public BasePokemon(BufferedImage sprite, int x, int y, String name, Handler handler) {
        super(sprite, x, y, name, handler);
    }
}
