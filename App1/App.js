import React, { Component } from 'react';
import {
    Alert,
	View,
	Text,
	Button,
	Platform,
	StyleSheet
} from 'react-native';

import { NativeModules } from "react-native";
const MemoryInfo = NativeModules.MemoryInfo;

import RNFS from 'react-native-fs';

// const readF = function() {
	// let rnfsPath = Platform.OS === 'ios'? RNFS.LibraryDirectoryPath: RNFS.ExternalDirectoryPath;
	// const path =  RNFS.DocumentDirectoryPath + '/ks.txt';
	// console.log(path);

	// RNFS.readFile(path, 'utf8')
	// 	.then((result) => {
	// 		console.log('读取成功');
	// 	})
	// 	.catch((err) => {
	// 		//alert(err.message);
	// 		console.log(err.message);
	// 	})

	// RNFS.readFileAssets('ks.txt')
	// 	.then((res) => { 
	// 		console.log('read file res: ', res); 
	// 		return res;
	// 	})
	// 	.then((res) => { 
	// 		console.log('read file: ', res); 
	// 	});
// };

export default class ToastTest extends Component {
    render() {
        return (
            <View style={{ flex: 1, flexDirection: 'column', justifyContent: "center", alignItems: "center" }}>
				<View style={{width: 100, height: 100, backgroundColor: 'transparent'}}>
					<Button 
						color="#205c42"
						title="All"
						onPress={() => { MemoryInfo.getAllMemory(x => {
							Alert.alert(
								'总容量',
								x.toString() + ' MB',
								[
									{text: 'OK'},
								]
							);
						});}} />
				</View>

				<View style={{width: 100, height: 100, backgroundColor: 'transparent'}}>
					<Button
						color="#205c42"
						title="Available"
						onPress={() => { MemoryInfo.getAvailableMemory(x => {
							Alert.alert(
								'可用容量',
								x.toString() + ' MB',
								[
									{text: 'OK'},
								]
							);
						});}} />
				</View>
				
				<View style={{width: 100, height: 100, backgroundColor: 'transparent'}}>
					<Button
						color="#205c42"
						title="Used"
						onPress={() => { MemoryInfo.getUsedMemory(x => {
							Alert.alert(
								'已用容量',
								x.toString() + ' MB',
								[
									{text: 'OK'},
								]
							);
						});}} />
				</View>
            </View>
        );
    }
}

var styles = StyleSheet.create({
	button: {
		width: 100,
		height: 100,
		padding: 20
	}
});