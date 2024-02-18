package com.noobcat.admin.service;

import org.openapitools.model.Dict;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.noobcat.admin.mapper.DictMapper;

@Service
public class DictService extends ServiceImpl<DictMapper, Dict> {
}
