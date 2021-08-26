package com.songbl.wonders.respository;

import com.songbl.wonders.bean.LogBean;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface LogBeanRepository extends ElasticsearchRepository<LogBean, String> {
}
