package Game.PacMan.entities.Statics;

import Main.Handler;
import Resources.Images;

public class BigDot extends BaseStatic{
    public BigDot(int x, int y, int width, int height, Handler handler) {
        super(x, y, width, height, handler, Images.pacmanDots[0]);
    }
}
