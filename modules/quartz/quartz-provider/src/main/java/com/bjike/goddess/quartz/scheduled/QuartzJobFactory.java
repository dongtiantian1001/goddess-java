package com.bjike.goddess.quartz.scheduled;

import com.alibaba.dubbo.config.ReferenceConfig;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.quartz.entity.ScheduleJob;
<<<<<<< HEAD
=======
import com.bjike.goddess.quartz.session.APISession;
>>>>>>> develop
import org.I0Itec.zkclient.ZkClient;
import org.apache.commons.lang3.StringUtils;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
<<<<<<< HEAD
import java.util.*;
=======
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
>>>>>>> develop


@DisallowConcurrentExecution
public class QuartzJobFactory implements Job {

    private static final Logger CONSOLE = LoggerFactory.getLogger(QuartzJobFactory.class);
    private static Map<String, Class<?>> maps = new HashMap<String, Class<?>>();
<<<<<<< HEAD
    private static Set<String> ADDRESS_SET = new HashSet<>(0);
=======
>>>>>>> develop

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        ScheduleJob scheduledJob = (ScheduleJob) context.getMergedJobDataMap().get("scheduleJob");
        CONSOLE.info("执行任务：" + scheduledJob.getName());
        try {
            String className = scheduledJob.getClazz();

            Class<?> exec = null;
            if (maps.containsKey(className)) {
                exec = maps.get(className);
                maps.put(className, exec);
            } else {
                ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
                if (null == classLoader) {
                    classLoader = QuartzJobFactory.class.getClassLoader();
                }
                exec = classLoader.loadClass(className);
                maps.put(className, exec);
            }
            try {
                invokeDubbo(scheduledJob);

            } catch (Exception e1) {
                e1.printStackTrace();
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void invokeDubbo(ScheduleJob scheduleJob) throws SerException {
        try {
            String url = null;//Dubbo服务暴露的ip地址&端口
<<<<<<< HEAD
            boolean exists = false;
            for (String ad : ADDRESS_SET) { // 缓存地址获取
                if (ad.indexOf(scheduleJob.getClazz()) <= 0) {
                    url = ad;
                    exists = true;
                    break;
                }
            }
            if (!exists) { //远程zookeeper获取
                ZkClient zk = new ZkClient("zookeeper.host.bjike.com:2181", 5000);
                List<String> url_list = zk.getChildren("/dubbo/" + scheduleJob.getClazz() + "/providers");
                zk.close();
                if (null != url_list && url_list.size() > 0) {
                    for (String ul : url_list) {
                        if (ul.indexOf(scheduleJob.getClazz()) <= 0) {
                            String realUrl = StringUtils.substringBefore(ul, "?");
                            if (!ADDRESS_SET.contains(realUrl)) {
                                ADDRESS_SET.add(realUrl);
=======
            url = APISession.get(scheduleJob.getClazz());
            if (StringUtils.isBlank(url)) { //远程zookeeper获取
                ZkClient zk = new ZkClient("45.76.206.84:2181", 5000);
                List<String> url_list = zk.getChildren("/dubbo/" + scheduleJob.getClazz() + "/providers");
                zk.close();
                if (null != url_list && url_list.size() > 0) {
                    try {
                        for (String ul : url_list) {
                            ul = URLDecoder.decode(ul, "utf-8");
                            if (ul.indexOf(scheduleJob.getClazz()) >= 0) {
                                String realUrl = StringUtils.substringBefore(ul, "?");
                                APISession.put(scheduleJob.getClazz(), realUrl);
>>>>>>> develop
                                url = realUrl;
                                break;
                            }
                        }
<<<<<<< HEAD
                    }
=======
                    } catch (Exception e) {
                        throw new SerException("解析url错误");
                    }

>>>>>>> develop
                } else {
                    throw new SerException(scheduleJob.getClazz() + "未注册");
                }

<<<<<<< HEAD

=======
>>>>>>> develop
            }
            if (null != url) {
                ReferenceConfig referenceConfig = new ReferenceConfig();
                referenceConfig.setTimeout(10000);
                referenceConfig.setInterface(Class.forName(scheduleJob.getClazz()));
                referenceConfig.setUrl(url);
                Object obj = referenceConfig.get();
                Method clazzMethod = obj.getClass().getMethod(scheduleJob.getMethod());
                Object o = clazzMethod.invoke(obj);
            } else {
                throw new SerException(scheduleJob.getClazz() + "未注册");
            }

        } catch (ClassNotFoundException e) {
<<<<<<< HEAD
            console.error(e.getMessage());
        } catch (NoSuchMethodException e) {
            console.error(e.getMessage());
        } catch (IllegalAccessException e) {
            console.error(e.getMessage());
        } catch (InvocationTargetException e) {
            console.error("Exception:" + e.getTargetException().getMessage());
=======
            CONSOLE.error(e.getMessage());
        } catch (NoSuchMethodException e) {
            CONSOLE.error(e.getMessage());
        } catch (IllegalAccessException e) {
            CONSOLE.error(e.getMessage());
        } catch (InvocationTargetException e) {
            CONSOLE.error("Exception:" + e.getTargetException().getMessage());
>>>>>>> develop
        }
    }


}