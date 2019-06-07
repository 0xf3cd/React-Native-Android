import React, { Component } from 'react';
import {
    Alert,
    View,
	Button,
	Platform,
	FlatList,
	StyleSheet,
	Text,
	ScrollView,
	TouchableNativeFeedback
} from 'react-native';
import NetInfo from "@react-native-community/netinfo";

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
	constructor(props) {
		super(props);
		this.state = { showText: '' };
	
		// 每1000毫秒对showText状态做一次取反操作
		// setInterval(() => {
		// 	this.setState(previousState => {
		// 		return { isShowingText: !previousState.isShowingText };
		// 	});
		// }, 1000);
	}

    render() {
		let content;
		if(this.state.showText === '') {
			content = (<Text></Text>);
		} else {
			content = (<Text>{this.state.showText}</Text>)
		}

        return (
            <View style={{flex: 1, flexDirection: 'column', justifyContent: "center", alignItems: "center"}}>
				<View style={{flex: 1, flexDirection: 'row', height: 50}}>
					<TouchableNativeFeedback
						onPress={() => {
							SysInfo.getCurrentLanguage(x => {
								this.setState(previousState => {
									return {showText: '当前使用语言为：\n' + x};
								});
							});	
						}}
						background={Platform.OS === 'android' ? TouchableNativeFeedback.SelectableBackground() : ''}>
						<View style={styles.button}>
							<Text style={styles.buttonText}>当前语言</Text>
						</View>
					</TouchableNativeFeedback>

					<TouchableNativeFeedback
						onPress={() => {
							SysInfo.getAllLanguages(x => {
								this.setState(previousState => {
									return {showText: '所有可选语言为：\n' + x};
								});
							});
						}}
						background={Platform.OS === 'android' ? TouchableNativeFeedback.SelectableBackground() : ''}>
						<View style={styles.button}>
							<Text style={styles.buttonText}>所有语言</Text>
						</View>
					</TouchableNativeFeedback>

					<TouchableNativeFeedback
						onPress={() => {
							SysInfo.getAllFonts(x => {
								this.setState(previousState => {
									return {showText: '所有可用字体为：\n' + x};
								});
							});
						}}
						background={Platform.OS === 'android' ? TouchableNativeFeedback.SelectableBackground() : ''}>
						<View style={styles.button}>
							<Text style={styles.buttonText}>所有字体</Text>
						</View>
					</TouchableNativeFeedback>
				</View>

				<View style={{flex: 1, flexDirection: 'row', height: 50}}>
					<TouchableNativeFeedback
						onPress={() => {
							SysInfo.getFontSize(x => {
								this.setState(previousState => {
									return {showText: '默认字体大小为：\n' + x};
								});
							});	
						}}
						background={Platform.OS === 'android' ? TouchableNativeFeedback.SelectableBackground() : ''}>
						<View style={styles.button}>
							<Text style={styles.buttonText}>字体大小</Text>
						</View>
					</TouchableNativeFeedback>

					<TouchableNativeFeedback
						onPress={() => {
							SysInfo.getDefaultInputMethod(x => {
								this.setState(previousState => {
									return {showText: '当前输入法为：\n' + x};
								});
							});
						}}
						background={Platform.OS === 'android' ? TouchableNativeFeedback.SelectableBackground() : ''}>
						<View style={styles.button}>
							<Text style={styles.buttonText}>当前输入法</Text>
						</View>
					</TouchableNativeFeedback>

					<TouchableNativeFeedback
						onPress={() => {
							SysInfo.getAllInputMethods(x => {
								this.setState(previousState => {
									return {showText: '所有可用输入法为：\n' + x};
								});
							});
						}}
						background={Platform.OS === 'android' ? TouchableNativeFeedback.SelectableBackground() : ''}>
						<View style={styles.button}>
							<Text style={styles.buttonText}>所有输入法</Text>
						</View>
					</TouchableNativeFeedback>
				</View>

				<View style={{flex: 1, flexDirection: 'row', height: 50}}>
				<TouchableNativeFeedback
						onPress={() => {
							SysInfo.getCurrentTime(x => {
								this.setState(previousState => {
									return {showText: '当前时间为：\n' + x};
								});
							});	
						}}
						background={Platform.OS === 'android' ? TouchableNativeFeedback.SelectableBackground() : ''}>
						<View style={styles.button}>
							<Text style={styles.buttonText}>当前时间</Text>
						</View>
					</TouchableNativeFeedback>

					<TouchableNativeFeedback
						onPress={async () => {
							SysInfo.getConnectionType(x => {
								this.setState(previousState => {
									return {showText: '网络连接情况为：\n' + x};
								});
							});
						}}
						background={Platform.OS === 'android' ? TouchableNativeFeedback.SelectableBackground() : ''}>
						<View style={styles.button}>
							<Text style={styles.buttonText}>网络连接状况</Text>
						</View>
					</TouchableNativeFeedback>

					<TouchableNativeFeedback
						onPress={async () => {
							SysInfo.turnOnWifi4G(x => {
								this.setState(previousState => {
									return {showText: x};
								});
							});
						}}
						background={Platform.OS === 'android' ? TouchableNativeFeedback.SelectableBackground() : ''}>
						<View style={styles.button}>
							<Text style={styles.buttonText}>打开网络连接</Text>
						</View>
					</TouchableNativeFeedback>
				</View>
				
				<View style={{flex: 10, flexDirection: 'column', justifyContent: "center", alignItems: "center" }}>
					<ScrollView style={{maxWidth: 300}}>
						{content}
					</ScrollView>
				</View>
            </View>
        );
    }
}

const styles = StyleSheet.create({
  container: {
    paddingTop: 60,
    alignItems: 'center'
  },
  button: {
	marginBottom: 10,
	margin: 5,
	width: 110,
	height: 40,
	justifyContent: "center",
    alignItems: 'center',
    backgroundColor: '#2196F3'
  },
  buttonText: {
    padding: 10,
    color: 'white'
  }
});