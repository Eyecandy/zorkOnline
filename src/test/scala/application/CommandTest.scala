package application

import builders.LevelBuilder
import command._

object CommandTest extends  App{
  LevelBuilder.createWorld()
  val player = Commands.player
  assert(command.Commands.useLink("old_door").equals("No such thing can opened"))
  println(Commands.moveDir("e"))
  assert(command.Commands.useLink("old_door").equals("That door is locked"))
  assert(Commands.attack("s").equals("No such thing to attack here"))
  assert(Commands.use("s").equals("No such item in inventory"))
  assert(Commands.useLink("old_door").equals("That door is locked"))
  assert(command.Commands.moveDir("s").equals("direction: South: a wind strong wind is coming from above<br>brass_key: it's rusty<br>wolfy: roaring,black fur fangs and stuff"))
  assert(command.Commands.attack("brass_key").equals("that is not a monster"))
  println(command.Commands.attack("wolfy"))





}
