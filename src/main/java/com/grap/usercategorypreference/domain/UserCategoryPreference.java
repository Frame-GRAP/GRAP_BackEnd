package com.grap.usercategorypreference.domain;

import com.grap.category.domain.Category;
import com.grap.user.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class UserCategoryPreference {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public void mapUser(User user) {
        this.user = user;
    }

    public void mapCategory(Category category) {
        this.category = category;
    }
}
