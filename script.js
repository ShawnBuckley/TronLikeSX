var colors = []
colors.push("#ff0000")
colors.push("blue")
colors.push("green")
colors.push("yellow")
colors.push("#000040") // darkblue

var spriteSheet = []

function addSpriteColor(color, sheet) {
	var newSheet = document.createElement('canvas')
	newSheet.height = 192
	newSheet.width = 192
	newContext = newSheet.getContext("2d")
	newContext.fillStyle = color
	newContext.fillRect(0, 0, 192, 192)
	newContext.drawImage(sheet, 0, 0)

	var imgData = newContext.getImageData(0, 0, 192, 192)
	var data = imgData.data
	for(var i=0; i<data.length; i+=4) {
		var r = data[i]
		var g = data[i+1]
		var b = data[i+2]
		// var a = data[i+3]
		if(r == 0 && g == 0 && b == 0) {
			data[i+3] = 0
		}
	}
	newContext.putImageData(imgData, 0, 0)

	spriteSheet.push(newSheet)
}

function onLoad() {
	console.log('loaded')
	var sprite = document.getElementById("sprite")

	for(var i=0; i<colors.length; i++) {
		console.log(colors[i])
		addSpriteColor(colors[i], sprite)
	}

	var canvas = document.getElementById('canvas')
	var context = canvas.getContext("2d")

	context.fillStyle = colors[4]
	context.fillRect(0, 0, 192, 192)
	
	context.drawImage(spriteSheet[3], 0, 0)
}