package com.example.demo.service.wish;

import com.example.demo.dto.wish.WishResponseDto;
import com.example.demo.entity.Product;
import com.example.demo.entity.User;
import com.example.demo.entity.Wish;
import com.example.demo.entity.WishId;
import com.example.demo.exception.DuplicateWishException;
import com.example.demo.exception.ProductNotFoundException;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.exception.WishNotFoundException;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.WishRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class WishServiceImpl implements WishService{

  private final WishRepository wishRepository;
  private final UserRepository userRepository;
  private final ProductRepository productRepository;

  public WishServiceImpl(WishRepository wishRepository, UserRepository userRepository,
      ProductRepository productRepository) {
    this.wishRepository = wishRepository;
    this.userRepository = userRepository;
    this.productRepository = productRepository;
  }

  @Override
  @Transactional
  public void saveWishProduct(User user, Long productId) {
    if (wishRepository.existsById(new WishId(user.getId(), productId))) {
      throw new DuplicateWishException("이미 찜한 상품입니다.");
    }
    Product product = productRepository.findById(productId)
                                       .orElseThrow(() -> new ProductNotFoundException("해당 상품이 존재하지 않습니다."));

    Wish wish = new Wish(new WishId(user.getId(), productId), user, product);
    wishRepository.save(wish);
  }

  @Override
  @Transactional
  public void deleteWishProduct(Long userId, Long productId) {
    WishId wishId = new WishId(userId, productId);
    wishRepository.deleteById(wishId);
  }

  @Override
  public List<WishResponseDto> getWishProductList(Long userId) {
    List<Wish> wishes = wishRepository.findAllById_UserId(userId);

    return wishes.stream()
                 .map(wish -> new WishResponseDto(
                     wish.getProduct().getName(),
                     wish.getProduct().getPrice(),
                     wish.getProduct().getImageUrl()
                 ))
                 .collect(Collectors.toList());
  }
}
