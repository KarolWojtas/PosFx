package mappers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.karol.posfx.enums.ProductId;
import com.karol.posfx.model.dto.OrderDto;
import com.karol.posfx.model.dto.OrderItemDto;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.time.ZonedDateTime;
import java.util.Arrays;

public class OrderSerializationTest {
    private ObjectMapper objectMapper = new ObjectMapper();
    private OrderDto order;
    @Before
    public void beforeEach(){
        order = new OrderDto();
        order.setCreated(ZonedDateTime.now());
        order.setId("123");
        order.setOrderer("Anonim");
        order.setOrderItems(Arrays.asList(new OrderItemDto(ProductId.BEER, 2)));
        order.setTotalPrice(20.99);

    }
    @Test
    public void testSerialization(){
        String json = null;
        try {
            json = objectMapper.writeValueAsString(order);
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
        OrderDto deserializedOrder = null;
        try {
            deserializedOrder = objectMapper.readValue(json, OrderDto.class);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        assertEquals(order.getCreated(), deserializedOrder.created);
        assertEquals(order.getOrderer(), deserializedOrder.orderer);
    }
}
