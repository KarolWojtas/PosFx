package mappers;

import com.karol.posfx.enums.Category;
import com.karol.posfx.enums.ProductId;
import com.karol.posfx.model.Drink;
import com.karol.posfx.model.Order;
import com.karol.posfx.model.OrderItem;
import com.karol.posfx.services.FileService;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.List;

public class FileServiceTest {
    private FileService fileService = FileService.getInstance();
    private Order order1;

    @Before
    public void beforeEach(){

        order1 = new Order();
        order1.setCreated(ZonedDateTime.now());
        order1.setId("123");
        order1.setOrderer("Anonim");
        order1.setTotalPrice(20.99);
        Drink beer = new Drink.Builder().productId(ProductId.BEER).price(8f)
                .volumeLiters(0.5f).category(Category.DRINK).build();
        order1.setOrderItems(Arrays.asList(new OrderItem(2, beer)));
    }
    @Test
    @Ignore
    public void testSaveFile(){
        fileService.saveOrderToFile(order1, "orders.json");
    }
    @Test
    @Ignore
    public void testReadFile(){
        List<Order> ordersFromFile = fileService.readOrdersFromFile("orders.json");
        System.out.println(ordersFromFile);
    }
}
