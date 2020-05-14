package local;

import local.config.kafka.KafkaProcessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class PolicyHandler{
    @Autowired
    CustomerManagementRepository customerManagementRepository;
    
    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverOrderReceived_PointSaving(@Payload OrderReceived orderReceived){
        int add;
        int pre;
        if(orderReceived.isMe()){
            System.out.println("##### listener PointSaving : " + orderReceived.toJson());

            CustomerManagement customer = customerManagementRepository.findById(orderReceived.getCustomerId()).get();
            add = orderReceived.getQty();
            System.out.println("##### 더해질포인트 : " + add);
            pre = customer.getPoint();
            System.out.println("##### 이전포인트 : " + pre);
            customer.setPoint(add+pre);
            customerManagementRepository.save(customer);
        }
    }

}
