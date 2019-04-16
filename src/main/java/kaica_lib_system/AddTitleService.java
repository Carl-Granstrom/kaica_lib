package kaica_lib_system;

import kaica_lib.entities.Title;
import kaica_lib.repositories.TitleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AddTitleService {

    @Autowired
    private TitleRepository titleRepository;

    @Transactional(readOnly=true)
    public List<Title> findFirst20(String name, int page, int size) {

        return this.titleRepository.findFirst20ByNameContaining(name);
    }

}
