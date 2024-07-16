package my.order_service.service;

import itemservice.entity.Item;
import itemservice.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import orderservice.entity.Delivery;
import orderservice.entity.Order;
import orderservice.entity.OrderItem;
import orderservice.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import userservice.entity.UserEntity;
import userservice.repository.UserRepository;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ItemRepository itemRepository;
    private final UserRepository userRepository;

    //주문
    @Transactional
    public Long order(Integer userId, Long itemId, int count) {

        //엔티티 조회
//        Member member = memberRepository.findOne(itemId);
        UserEntity users = userRepository.findOne(userId);
        Item item = itemRepository.findOne(itemId);

        //배송정보 생성
        Delivery delivery = new Delivery();
        delivery.setAddress(users.getAddress());

        //주문상품 생성
        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);
        //protected 되어있음! 생성하지 마라!(유지보수 할때 protected 되어있는거 보고 생성자 쓰지 말라는거!
        //OrderItem orderItem1 = new OrderItem();
        //주문생성 Order도 마찬가지!

        //주문 생성
        Order order = Order.createOrder(users, delivery, orderItem);

        //주문 저장
        //원래는 딜리버리, 오더아이템 persist해줘야됨, Jpa에 넣어주고 값 세팅해줘야함.
        //order만 save해도 되는게 Order에서 cascade 걸어줬기때문.
        //order가 persist될때 딜리버리와 오더아이템 둘다 persist됨!
        orderRepository.save(order);

        return order.getId();
    }


    //주문 취소
    @Transactional
    public void cancelOrder(Long orderId) {
        //주문 엔티티 조회
        Order order = orderRepository.findOne(orderId);
        //주문 취소
        order.cancel();

        //Jpa의 강점 : 상태 변경시 메서드 밖에서 재고변경, 상태변경 쿼리를 직접 작성하여 올려야되는데 얜 안해도됨!
        //엔티티 안의 데이터만 바꾸면 jpa가 바뀐 포인트를 더티체킹하여 데이터베이스에 업데이트쿼리를 촥촥날림.
    }

    //https://www.inflearn.com/course/lecture?courseSlug=%EC%8A%A4%ED%94%84%EB%A7%81%EB%B6%80%ED%8A%B8-JPA-%ED%99%9C%EC%9A%A9-1&unitId=24299&category=questionDetail&q=364228
    //주문서비스 개발 16분정도부터? 설명참고.

    //검색
//    public List<Order> findOrders
}
