import React, { Component } from 'react';
import {
    Alert,
    View,
	Button,
	Platform,
	StyleSheet
} from 'react-native';

import { NativeModules } from "react-native";
const SysInfo = NativeModules.SysInfo;

// import RNFS from 'react-native-fs';
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

export default class App extends Component {
    render() {
        return (
            <View style={{ flex: 1, flexDirection: 'column', justifyContent: "center", alignItems: "center" }}>
				<Button 
					color="#205c42"
					title="Test"
					onPress={() => {
						// SysInfo.show('14');
						// SysInfo.getLanguage();
						SysInfo.getAllInputMethods();
					}} />
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