package com.awesomeproject;

import java.io.File;
 
import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.os.StatFs;
import android.text.format.Formatter;
import android.view.View;
import android.widget.TextView;

class ROMInfo {
    /**
     * 获取 ROM 总大小
     * @return {int} KB 为单位
     */
    public int getTotalRomSize() {
        // 获取根目录
        File root = Environment.getRootDirectory();
        StatFs stat = new StatFs(root.getPath());

        // 每个 block 大小
        long blockSize = stat.getBlockSize(); // 字节为单位

        // 获取总的 block 数
        long totalBlocks = stat.getBlockCount();
        return (int) (totalBlocks * blockSize / (1024 * 1024)); // MB
    }

    /**
     * 获取可用 ROM 空间大小
     * @return {int} KB 为单位
     */
    public int getAvailableRomSize() {
        // 获取根目录
        File root = Environment.getRootDirectory();  
        StatFs stat = new StatFs(root.getPath());

        // 每个 block 大小
        long blockSize = stat.getBlockSize(); // 字节为单位

        // 获取总的 block 数
        long availableBlocks = stat.getAvailableBlocks();
        return (int) (availableBlocks * blockSize / (1024 * 1024)); // MB
    }

    /**
     * 获取已用 ROM 空间大小
     * @return {int} KB 为单位
     */
    public int getUsedRomSize() {
        int totalSize = this.getTotalRomSize(); // 总空间
        int avaiSize = this.getAvailableRomSize(); // 可用空间

        return totalSize - avaiSize; // MB
    }
}