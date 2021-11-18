package bridgePattern;

import lombok.AllArgsConstructor;

/**
 * @author: gaochen
 * Date: 2019/1/15
 */
@AllArgsConstructor
public abstract class Computer {
    protected Brand brand;

    public void info() {
        this.brand.info();
    }
}
