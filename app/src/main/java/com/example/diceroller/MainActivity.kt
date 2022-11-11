package com.example.diceroller

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.core.view.isVisible

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId", "SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //always starts with 1 dice
        var numDices = 1

        // References activity
        val rollButton: Button = findViewById(R.id.buttonRoll)
        val addButton: Button = findViewById(R.id.buttonAddDice)
        val removeButton: Button = findViewById(R.id.buttonRemoveDice)

        //Starting invisible max dices
        val dice2Image: ImageView = findViewById(R.id.imageView2)
        dice2Image.setImageResource(R.drawable.dice_2)
        dice2Image.isVisible = false
        val dice3Image: ImageView = findViewById(R.id.imageView3)
        dice3Image.setImageResource(R.drawable.dice_3)
        dice3Image.isVisible = false
        val dice4Image: ImageView = findViewById(R.id.imageView4)
        dice4Image.setImageResource(R.drawable.dice_4)
        dice4Image.isVisible = false


        // setOnClickListener{} is a lambida = What you need to know for now is that within the curly braces, you put instructions for what should happen when the button is tapped. You'll have your app display a Toast, which is a brief message in the next step.
        // its different from setOnClickListener() --- no curly braces

        // Roll button listeners
        rollButton.setOnClickListener { rollDice(dice2Image, dice3Image, dice4Image) }

        //add Button listener
        addButton.setOnClickListener {

            if(numDices <= 4){
            numDices += 1
            addDice(numDices, dice2Image, dice3Image, dice4Image)
                if (numDices > 4) numDices = 4
           }

        }

        //remove Button listener
        removeButton.setOnClickListener {
            if(numDices >= 1){
                removeDice(numDices)
                numDices -= 1
                    if(numDices < 1) {numDices = 1}
            }
        }

            //val toast = Toast.makeText(this, "Dice Rolled!", Toast.LENGTH_SHORT) // app display a Toast, which is a brief message that appears to the user.
            //toast.show()
            //Toast.makeText(this, "Dice Rolled!", Toast.LENGTH_SHORT).show() // combining the tow lines below in one line
        rollDice(dice2Image, dice3Image, dice4Image) // initialize the app with a random dice roll
    }

    private fun removeDice(numDices: Int) {
        if( numDices == 2 ){
            val dice2Image: ImageView = findViewById(R.id.imageView2)
            dice2Image.isVisible = false
        } else if(numDices == 3) {
            val dice3Image: ImageView = findViewById(R.id.imageView3)
            dice3Image.isVisible = false
        } else if(numDices == 4) {
            val dice4Image: ImageView = findViewById(R.id.imageView4)
            dice4Image.isVisible = false
        } else{
            Toast.makeText(this, "You cant delete the first dice.", Toast.LENGTH_SHORT).show()
        }
    }

    // making dices visible in the screen
    private fun addDice(
        numDices: Int,
        dice2Image: ImageView,
        dice3Image: ImageView,
        dice4Image: ImageView
    ) {
        if( numDices == 2 ){
            dice2Image.isVisible = true
        } else if(numDices == 3) {
            dice3Image.isVisible = true
        } else if(numDices == 4) {
            dice4Image.isVisible = true
        } else {
            Toast.makeText(this, "You can only add 4 dices.", Toast.LENGTH_SHORT).show()
        }

    }

    /**
     * Roll the dice and update the screen with the result.
     */
    private fun rollDice(dice2Image: ImageView, dice3Image: ImageView, dice4Image: ImageView) {
        // Create new Dice object with 6 sides and roll the dice
        val dice = Dice(6)
        val diceRoll = dice.roll()
        val dice2Roll = dice.roll()
        val dice3Roll = dice.roll()
        val dice4Roll = dice.roll()

        // Find the ImageView in the layout first dice
        val diceImage: ImageView = findViewById(R.id.imageView1) // referencia do activity

        // Determine which drawable resource ID to use based on the dice roll
        val drawableResource = when (diceRoll) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }

        // Update the ImageView with the correct drawable resource ID
        diceImage.setImageResource(drawableResource)

        // Update the content description
        diceImage.contentDescription = diceRoll.toString()

        // rolling dice 2
        val drawable2Resource = when (dice2Roll) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }
        dice2Image.setImageResource(drawable2Resource)
        dice2Image.contentDescription = dice2Roll.toString()

        // rolling dice 3
        val drawable3Resource = when (dice3Roll) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }
        dice3Image.setImageResource(drawable3Resource)
        dice3Image.contentDescription = dice3Roll.toString()

        // rolling dice 4
        val drawable4Resource = when (dice4Roll) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }
        dice4Image.setImageResource(drawable4Resource)
        dice4Image.contentDescription = dice4Roll.toString()

    }


}

class Dice(private val numSides: Int) {

    fun roll(): Int {
        return (1..numSides).random()
    }
}