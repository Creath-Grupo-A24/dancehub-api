package br.com.dancehub.api.usecases.subscription.create;


import br.com.dancehub.api.subscription.SubscriptionEntity;
import br.com.dancehub.api.subscription.SubscriptionRepository;
import br.com.dancehub.api.event.Category;
import br.com.dancehub.api.event.CategoryRepository;
import br.com.dancehub.api.event.EventEntity;
import br.com.dancehub.api.event.EventRepository;
import br.com.dancehub.api.exceptions.NotFoundEntityException;
import br.com.dancehub.api.user.User;
import br.com.dancehub.api.user.UserRepository;
import br.com.dancehub.api.utils.UUIDUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CreateSubscriptionUseCase {
    private final SubscriptionRepository subscriptionRepository;
    private final CategoryRepository categoryRepository;
    private final EventRepository eventRepository;
    private final UserRepository userRepository;


    public UUID execute(CreateSubscriptionRequest request) {
        String name = request.name();
        String description = request.description();
        LocalDateTime time = request.time();
        Category category = this.categoryRepository.findById(Integer.parseInt(request.category()))
                        .orElseThrow(() -> new NotFoundEntityException(Category.class, request.category()));
        EventEntity event = eventRepository.findById(UUIDUtils.getFromString(request.event_id()))
                .orElseThrow(() -> new NotFoundEntityException(EventEntity.class, request.event_id()));
        List<User> staff = request.dancers_id()
                .stream()
                .map(s -> userRepository.findById(UUID.fromString(s))
                        .orElseThrow(() -> new NotFoundEntityException(User.class, s))).toList();

        final SubscriptionEntity entity = SubscriptionEntity.builder()
                .name(name)
                .description(description)
                .time(time)
                .category(category)
                .event(event)
                .staff(staff).build();

        return subscriptionRepository.save(entity).getId();
    }
}
