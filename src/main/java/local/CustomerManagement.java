package local;

import javax.persistence.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.BeanUtils;
import java.util.List;

@Entity
@Table(name="CustomerManagement_table")
public class CustomerManagement {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long customerId;
    private String Name;
    private boolean validated;
    private int point;
    private String status;


    @PostPersist
    public void eventPublish() throws JsonProcessingException {
        System.out.println("##### 회원가입시작");
        MembershipRegistered membershipRegistered = new MembershipRegistered();
        BeanUtils.copyProperties(this, membershipRegistered);
        membershipRegistered.publish();

    }

//    @PostUpdate
//    public void onPostUpdate() throws JsonProcessingException {
//        if ("withdraw".equals(this.getStatus())) {
//                System.out.println("##### 탈퇴시작");
//                MembershipWithdrawed membershipWithdrawed = new MembershipWithdrawed();
//                BeanUtils.copyProperties(this, membershipWithdrawed);
//                membershipWithdrawed.publish();
//        }
//    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public boolean isValidated() {
        return validated;
    }

    public void setValidated(boolean validated) {
        this.validated = validated;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
