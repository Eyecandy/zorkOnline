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
  command.Commands.moveDir("s")
 // assert(command.Commands.attack("brass_key").equals("that is not a monster"))
  assert(command.Commands.attack("brass_key").equals("that is not a monster"))
  println(command.Commands.attack("wolfy"))



}
