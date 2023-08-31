package engine.data.transfer.data.loader;

import engine.generated.PRDWorld;

import java.io.FileNotFoundException;

public interface DataLoader {

    public PRDWorld loadData(String string) throws FileNotFoundException;

}

