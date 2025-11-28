package net.datasa.homework1.service;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import net.datasa.homework1.dto.MemberDTO;
import net.datasa.homework1.entity.MemberEntity;
import net.datasa.homework1.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional
public class MemberService {

    @Autowired
    MemberRepository repository;

    public void insertMember(MemberDTO dto) {
        MemberEntity entity = new MemberEntity();
        entity.setId(dto.getId());
        entity.setPw(dto.getPw());
        entity.setName(dto.getName());
        entity.setPhone(dto.getPhone());

        repository.save(entity);
    }

    public boolean checkLogin(String id, String pw) {
        Optional<MemberEntity> opt = repository.findById(id);
        if(opt.isEmpty()){
            return false;
        }
        MemberEntity entity = opt.get();
        return entity.getPw().equals(pw);
    }

    public List<MemberEntity> listAll() {
        return repository.findAll();
    }
}
