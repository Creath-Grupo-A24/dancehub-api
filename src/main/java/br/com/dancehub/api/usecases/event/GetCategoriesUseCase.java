package br.com.dancehub.api.usecases.event;

import br.com.dancehub.api.contexts.event.category.Category;
import br.com.dancehub.api.contexts.event.category.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class GetCategoriesUseCase {

    private final CategoryRepository categoryRepository;

    public List<Category> execute() {
        return this.categoryRepository.findAll();
    }

}
