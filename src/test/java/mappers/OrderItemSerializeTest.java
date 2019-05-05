package mappers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.karol.posfx.enums.ProductId;
import com.karol.posfx.model.dto.OrderItemDto;
import org.junit.Ignore;
import org.junit.Test;

import java.nio.file.FileSystems;

import static org.junit.Assert.*;

public class OrderItemSerializeTest {
    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    @Ignore
    public void testSerialization(){
        OrderItemDto item = new OrderItemDto();
        item.setProductId(ProductId.BEER);
        item.setQuantity(2);
        String json = null;
        try{
            json = objectMapper.writeValueAsString(item);
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
        OrderItemDto deserializedItem = null;
        try {
            deserializedItem = objectMapper.readValue(json, OrderItemDto.class);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        assertEquals(item.quantity, deserializedItem.quantity);
        assertEquals(item.productId, deserializedItem.productId);
        System.out.println(FileSystems.getDefault().getPath("").toAbsolutePath());
    }
}
