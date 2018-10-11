package com.epam.spring.hometask.dao.aspect;

import com.epam.spring.hometask.domain.User;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Pointcut;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;

public class DiscountAspect {

  private Map<Byte, Integer> discountsCounter;
  private Map<User, Map<Byte, Integer>> userDiscounts;

  public DiscountAspect() {
    discountsCounter = new HashMap<>();
    userDiscounts = new HashMap<>();
  }

  /** Returns how many times each discount was given in total. */
  public Map<Byte, Integer> getDiscountsCounter() {
    return discountsCounter;
  }

  /** Returns how many times each discount was given for concrete {@link User}. */
  public Map<User, Map<Byte, Integer>> getUserDiscounts() {
    return userDiscounts;
  }

  @Pointcut(
      "execution(* com.epam.spring.hometask.service.discount.DiscountService+.getDiscount(..))")
  private void discountServiceGetDiscount() {}

  @AfterReturning(
      pointcut = "discountServiceGetDiscount() && args(user,..)",
      returning = "discount")
  public void countDiscount(@Nullable User user, byte discount) {
    discountsCounter.putIfAbsent(discount, 0);
    discountsCounter.put(discount, discountsCounter.get(discount) + 1);

    if (user != null) {
      Map<Byte, Integer> counterMap = userDiscounts.computeIfAbsent(user, k -> new HashMap<>());
      counterMap.putIfAbsent(discount, 0);
      counterMap.put(discount, counterMap.get(discount) + 1);
    }
  }
}
