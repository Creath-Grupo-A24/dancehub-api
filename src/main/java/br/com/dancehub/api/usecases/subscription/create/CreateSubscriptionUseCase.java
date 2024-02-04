package br.com.dancehub.api.usecases.subscription.create;


import br.com.dancehub.api.contexts.event.category.Category;
import br.com.dancehub.api.contexts.event.category.CategoryRepository;
import br.com.dancehub.api.contexts.event.EventEntity;
import br.com.dancehub.api.contexts.event.EventRepository;
import br.com.dancehub.api.contexts.subscription.SubscriptionEntity;
import br.com.dancehub.api.contexts.subscription.SubscriptionRepository;
import br.com.dancehub.api.contexts.user.User;
import br.com.dancehub.api.contexts.user.UserRepository;
import br.com.dancehub.api.shared.exceptions.NotFoundEntityException;
import br.com.dancehub.api.shared.utils.UUIDUtils;
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
        final String name = request.name();
        final String description = request.description();
        final LocalDateTime time = request.time();
        final Integer categoryId = request.categoryId();
        final Category category = this.categoryRepository.findById(categoryId)
                        .orElseThrow(() -> new NotFoundEntityException(Category.class, categoryId.toString()));
        final EventEntity event = eventRepository.findById(UUIDUtils.getFromString(request.event_id()))
                .orElseThrow(() -> new NotFoundEntityException(EventEntity.class, request.event_id()));
        final List<User> staff = this.userRepository.findAllById(request.staff_id().stream().map(UUIDUtils::getFromString).toList());

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
