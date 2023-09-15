package com.example.wms.service.impl;

import com.example.wms.common.Result;
import com.example.wms.entity.Storage;
import com.example.wms.entity.User;
import com.example.wms.mapper.StorageMapper;
import com.example.wms.service.IStorageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wms
 * @since 2023-08-17
 */
@Service
public class StorageServiceImpl extends ServiceImpl<StorageMapper, Storage> implements IStorageService {

}
