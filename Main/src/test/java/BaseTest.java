import com.core.base.CoreBase;
import com.core.listeners.Listener;
import com.core.utilities.CoreUtils;
import org.testng.annotations.*;

@Listeners(Listener.class)
public class BaseTest extends CoreBase {
    CoreUtils coreUtils;

    protected BaseTest() {
        super();
    }

    @BeforeTest
    public void setup() {
        initialization();
        coreUtils = new CoreUtils(getDriver());
    }

    @AfterTest
    public void quit() {
        getDriver().quit();
    }
}