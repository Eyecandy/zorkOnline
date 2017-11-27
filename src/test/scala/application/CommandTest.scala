package application

import builders.LevelBuilder
import command._

object CommandTest extends  App{
  LevelBuilder.createWorld()
  val player = Commands.player
  assert(command.Commands.useLink("old_door").equals(CommandStrings.cantOpenThatObject))
  println(Commands.moveDir("e"))
  assert(command.Commands.useLink("old_door").equals(CommandStrings.lockedDoor ))
  assert(Commands.attack("s").equals(CommandStrings.noSuchThingToAttack))
  assert(Commands.use("s").equals(CommandStrings.noSuchItemInventory))
  assert(Commands.useLink("old_door").equals(CommandStrings.lockedDoor ))
  assert(command.Commands.moveDir("s").equals("direction: South: a wind strong wind is coming from above<br>brass_key: it's rusty<br>wolfy: roaring,black fur fangs and stuff"))
  assert(command.Commands.attack("brass_key").equals("that is not a monster"))
  println(command.Commands.attack("wolfy"))



}
