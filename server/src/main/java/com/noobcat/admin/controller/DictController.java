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
  public ResponseEntity<DictPage> page(Integer current, Integer size, String name) {
    IPage<Dict> page = dictService.page(new Page<Dict>(current, size),
        Wrappers.<Dict>lambdaQuery()
            .like(!ObjectUtils.isEmpty(name), Dict::getName, name)
            .eq(Dict::getPid, "0"));
    return ResponseEntity.ok(new ModelMapper().map(page, DictPage.class));
  }

  @Override
  public ResponseEntity<List<Dict>> getList(String pid) {
    return ResponseEntity
        .ok(dictService.list(
            Wrappers.<Dict>lambdaQuery()
                .eq(ObjectUtils.isEmpty(pid), Dict::getPid, pid)));
  }

  @Override
  public ResponseEntity<Dict> getDict(String id) {
    return ResponseEntity.ok(dictService.getById(id));
  }

  @Override
  public ResponseEntity<Dict> createDict(Dict dict) {
    dictService.save(dict);
    return ResponseEntity.created(URI.create("/dict/" + dict.getId())).build();
  }

  @Override
  public ResponseEntity<Dict> updateDict(String id, Dict dict) {
    dict.setId(id);
    dictService.updateById(dict);
    return ResponseEntity.created(URI.create("/dict/" + dict.getId())).build();
  }

  @Override
  public ResponseEntity<Void> toggleEnable(String type) {
    return ResponseEntity.ok(null);
  }
}
