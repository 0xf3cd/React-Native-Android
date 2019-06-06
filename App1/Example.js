
// import React, { Component } from 'react';
// import { Image, AppRegistry, Text, TextInput, View } from 'react-native';

// import { NativeModules } from "react-native";
// // 下一句中的ToastExample即对应上文
// // public String getName()中返回的字符串
// const ToastExample = NativeModules.ToastExample;


// class Greeting extends Component {
//   render() {
//     return (
//       <View style={{alignItems: 'center', marginTop: 50}}>
//         <Text>Hello {this.props.name}!</Text>
//       </View>
//     );
//   }
// }

// class LotsOfGreetings extends Component {
//   render() {
//     return (
//       <View style={{alignItems: 'center'}}>
//         <Greeting name='Rexxar' />
//         <Greeting name='Jaina' />
//         <Greeting name='Valeera' />
//         <Text>You, {this.props.bitchName}, Bitch!</Text>
//       </View>
//     );
//   }
// }

// class Blink extends Component {
//   constructor(props) {
//     super(props);
//     this.state = { isShowingText: true };

//     // 每1000毫秒对showText状态做一次取反操作
//     setInterval(() => {
//       this.setState(previousState => {
//         return { isShowingText: !previousState.isShowingText };
//       });
//     }, 1000);
//   }

//   render() {
//     // 根据当前showText的值决定是否显示text内容
//     if (!this.state.isShowingText) {
//       return null;
//     }

//     return (
//       <Text>{this.props.text}</Text>
//     );
//   }
// }

// const f1 = () => {
//   return 15 + 16 + 19;
// };

// class BlinkApp extends Component {
//   render() {
//     return (
//       <View>
//         {/* <Blink text='I love to blink' />
//         <Blink text='Yes blinking is so great' />
//         <Blink text='Why did they ever take this out of HTML' />
//         <Blink text='Look at me look at me look at me' /> */}
//         <Blink text={ToastExample.show("Awesome", ToastExample.SHORT)} />
//       </View>
//     );
//   }
// }

// class HelloWorldApp extends Component {
//   render() {
//     // let pic = {
//     //   uri: 'https://upload.wikimedia.org/wikipedia/commons/d/de/Bananavarieties.jpg'
//     // };
//     const pic = require('./images/1.png');

//     return (
//         <View>
//           <Text>Hello, Gyan!</Text>
//           <Image source={pic} style={{width: 193, height: 110}} />
//           <LotsOfGreetings bitchName='Jake' />
//           <BlinkApp />
//         </View>
//     );
//   }
// }

// export default class PizzaTranslator extends Component {
//   constructor(props) {
//     super(props);
//     this.state = {text: ''};
//   }

//   render() {
//     return (
//       // style={{padding: 10}}
//       <View  style={{ flex: 1, justifyContent: "center", alignItems: "center" }}>
//         <HelloWorldApp />
//         <TextInput
//           style={{height: 40}}
//           placeholder="Type here to translate!"
//           onChangeText={(text) => this.setState({text})}
//         />
//         <Text style={{padding: 10, fontSize: 42}}>
//           {this.state.text.split(' ').map((word) => word && '🍕').join(' ')}
//         </Text>
//       </View>
//     );
//   }
// }






// import React, { Component } from 'react';
// import { Alert, AppRegistry, Button, StyleSheet, View } from 'react-native';

// export default class ButtonBasics extends Component {
//   _onPressButton() {
//     Alert.alert('You tapped the button!')
//   }

//   render() {
//     return (
//       <View style={styles.container}>
//         <View style={styles.buttonContainer}>
//           <Button
//             onPress={this._onPressButton}
//             title="Press Me"
//           />
//         </View>
//         <View style={styles.buttonContainer}>
//           <Button
//             onPress={this._onPressButton}
//             title="Press Me"
//             color="#841584"
//           />
//         </View>
//         <View style={styles.alternativeLayoutButtonContainer}>
//           <Button
//             onPress={this._onPressButton}
//             title="This looks great!"
//           />
//           <Button
//             onPress={this._onPressButton}
//             title="OK!"
//             color="#841584"
//           />
//         </View>
//       </View>
//     );
//   }
// }

// const styles = StyleSheet.create({
//   container: {
//    flex: 1,
//    justifyContent: 'center',
//   },
//   buttonContainer: {
//     margin: 20
//   },
//   alternativeLayoutButtonContainer: {
//     margin: 20,
//     flexDirection: 'row',
//     justifyContent: 'space-between'
//   }
// })

// skip this line if using Create React Native App
// AppRegistry.registerComponent('AwesomeProject', () => ButtonBasics);