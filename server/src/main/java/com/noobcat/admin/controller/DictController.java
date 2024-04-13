package com.noobcat.admin.controller;

import java.net.URI;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.openapitools.api.DictApi;
import org.openapitools.model.Dict;
import org.openapitools.model.DictPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.noobcat.admin.service.DictService;

@RestController
public class DictController implements DictApi {

  @Autowired
  private DictService dictService;

  @Override
  public ResponseEntity<DictPage> dictPage(Integer size, Integer current, String name) {
    IPage<Dict> page = dictService.page(new Page<Dict>(current, size),
        Wrappers.<Dict>lambdaQuery()
            .like(!ObjectUtils.isEmpty(name), Dict::getName, name)
            .eq(Dict::getParent, "0"));
    return ResponseEntity.ok(new ModelMapper().map(page, DictPage.class));
  }

  @Override
  public ResponseEntity<List<Dict>> getDictList(String parent) {
    return ResponseEntity
        .ok(dictService.list(
            Wrappers.<Dict>lambdaQuery()
                .eq(ObjectUtils.isEmpty(parent), Dict::getParent, parent)));
  }

  @Override
  public ResponseEntity<Dict> getDict(String uuid) {
    return ResponseEntity.ok(dictService.getOne(
        Wrappers.<Dict>lambdaQuery().eq(Dict::getUuid, uuid)));
  }

  @Override
  public ResponseEntity<Dict> createDict(Dict dict) {
    dictService.save(dict);
    return ResponseEntity.created(URI.create("/dict/" + dict.getUuid())).build();
  }

  @Override
  public ResponseEntity<Dict> updateDict(String uuid, Dict dict) {
    dict.setUuid(uuid);
    dictService.updateById(dict);
    return ResponseEntity.created(URI.create("/dict/" + uuid)).build();
  }

  @Override
  public ResponseEntity<Void> dictToggleEnable(String type) {
    return ResponseEntity.ok(null);
  }
}
