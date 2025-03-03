package ru.sayap.vinylka.service.vinyl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sayap.vinylka.persistence.cart.CartEntity;
import ru.sayap.vinylka.persistence.model.CartItemsEntity;
import ru.sayap.vinylka.persistence.model.UserEntity;
import ru.sayap.vinylka.persistence.model.VinylEntity;
import ru.sayap.vinylka.persistence.repository.VinylRepository;
import ru.sayap.vinylka.service.cart.CartService;
import ru.sayap.vinylka.service.vinyl.mapper.VinylServiceMapper;
import ru.sayap.vinylka.service.vinyl.model.VinylModel;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.UUID;
import java.util.function.Supplier;


@Service
public class VinylService {

    private final VinylRepository vinylRepository;
    private final VinylServiceMapper vinylServiceMapper;

    @Autowired
    public VinylService(VinylRepository vinylRepository, VinylServiceMapper vinylServiceMapper) {
        this.vinylRepository = vinylRepository;
        this.vinylServiceMapper = vinylServiceMapper;
    }

    public void save(VinylModel vinylModel) {
        vinylRepository.save(vinylServiceMapper.toEntity(vinylModel));
    }

    public List<VinylModel> findAll() {
        return vinylRepository.findAll()
                .stream()
                .map(vinylServiceMapper::toVinylEntityDto)
                .toList();
    }

    public VinylModel findById(UUID id) {
//        var test = new CartService() {
//            @Override
//            public CartEntity getCart(UserEntity userEntity) {
//                return null;
//            }
//
//            @Override
//            public void removeItemFromCart(UserEntity user, VinylEntity vinyl) {
//
//            }
//
//            @Override
//            public Set<CartItemsEntity> viewCartItems(UserEntity userEntity) {
//                return Set.of();
//            }
//        };


        return vinylRepository.findById(id)
                .map(vinylServiceMapper::toVinylEntityDto)
                //.orElseThrow(NoSuchElementException::new);
                .orElseThrow(() -> new NoSuchElementException("No value present"));
    }


}
