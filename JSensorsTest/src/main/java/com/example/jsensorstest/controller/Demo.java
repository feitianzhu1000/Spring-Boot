package com.example.jsensorstest.controller;

import io.github.pandalxb.jsensors.JSensors;
import io.github.pandalxb.jsensors.model.components.Components;
import io.github.pandalxb.jsensors.model.components.Cpu;
import io.github.pandalxb.jsensors.model.components.Disk;
import io.github.pandalxb.jsensors.model.sensors.Temperature;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author dwt
 * @version V1.0
 * @Package com.example.jsensorstest.controller
 * @date 2026/3/7 22:26
 * @description:
 */
@Controller
@RequestMapping("/demo")
public class Demo {
    @RequestMapping("/index")
    @ResponseBody
    public String index(){
        Components components = JSensors.get.components();

        List<Cpu> cpus = components.cpus;
        if (cpus != null) {
            for (final Cpu cpu : cpus) {
                System.out.println("Found CPU component: " + cpu.name);
                if (cpu.sensors != null) {
                    System.out.println("Sensors: ");
                    //Print temperatures
                    List<Temperature> temps = cpu.sensors.temperatures;
                    for (final Temperature temp : temps) {
                        System.out.println(temp.name + ": " + temp.value + " C");
                    }
                }
            }
        }

        List<Disk> disks = components.disks;
        if (disks != null) {
            for (final Disk disk : disks) {
                System.out.println("Found Disk component: " + disk.name);
                if (disk.sensors != null) {
                    System.out.println("Sensors: ");
                    //Print temperatures
                    List<Temperature> temps = disk.sensors.temperatures;
                    for (final Temperature temp : temps) {
                        System.out.println(temp.name + ": " + temp.value + " C");
                    }
                }
            }
        }

        return components.toString();
    }

}
