package com.example.finalspring.service.Impl;
import com.example.finalspring.dto.DojoDto;
import com.example.finalspring.entity.Dojo;
import com.example.finalspring.mapper.DojoMapper;
import com.example.finalspring.repository.DojoRepository;
import com.example.finalspring.service.DojoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Service
public class DojoServiceImpl implements DojoService {
    private final DojoRepository dojoRepository;
    private final DojoMapper dojoMapper;

    @Override
    public List<DojoDto> getAll() {
        return dojoMapper.toDtoList(dojoRepository.findAll());
    }

    @Override
    public DojoDto getById(Long id) {
        return dojoMapper.toDto(dojoRepository.findById(id).orElse(null));
    }

    @Override
    public DojoDto addDojo(DojoDto dojoDto) {
        return dojoMapper.toDto(dojoRepository.save(dojoMapper.toEntity(dojoDto)));
    }

    @Override
    public DojoDto updateDojo(Long id, DojoDto dojoDto) {

        Dojo dojo = dojoRepository.findById(id).orElse(null);
        if (!Objects.isNull(dojo)){
            dojo.setName(dojoDto.getName());
//            dojo.setSamuraiList(dojo.getSamuraiList());
            return dojoMapper.toDto(dojoRepository.save(dojo));
        }else {
            throw new RuntimeException("Dojo with id " + id + " not found");
        }
    }

    @Override
    public boolean deleteDojo(Long id) {
        Dojo dojo = dojoRepository.findById(id).orElse(null);
        if (dojo == null){
            return false;
        }
        dojoRepository.delete(dojo);
        return true;
    }
}
