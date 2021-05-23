package com.grap.category.domain;

import com.grap.categorytab.domain.CategoryTab;
import com.grap.gameandcategory.domain.GameAndCategory;
import com.grap.usercategorypreference.domain.UserCategoryPreference;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, name = "ui_name")
    private String uiName;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<GameAndCategory> gameAndCategory = new ArrayList<>();

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<CategoryTab> categoryTabs = new ArrayList<>();

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<UserCategoryPreference> userCategoryPreferences = new ArrayList<>();

    @Builder
    public Category(String name, String uiName) {
        this.name = name;
        this.uiName = uiName;
    }

    public void update(String name, String uiName) {
        this.name = name;
        this.uiName = uiName;
    }
}
