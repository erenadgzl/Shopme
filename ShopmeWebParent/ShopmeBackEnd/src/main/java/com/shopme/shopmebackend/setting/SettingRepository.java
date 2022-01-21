package com.shopme.shopmebackend.setting;

import com.shopme.shopme.common.entity.setting.Setting;
import com.shopme.shopme.common.entity.setting.SettingCategory;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SettingRepository extends CrudRepository<Setting, String> {
	List<Setting> findByCategory(SettingCategory category);
}
