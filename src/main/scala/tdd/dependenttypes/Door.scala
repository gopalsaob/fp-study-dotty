package tdd.dependenttypes

enum DoorState:
  case Open
  case Closed
import DoorState.*

trait Door[D <: DoorState]:
  type S = D

type OppositeDoorState[S <: DoorState] <: DoorState =
  S match
    case Open.type   => Closed.type
    case Closed.type => Open.type

object Door:
  def apply[S <: DoorState]: Door[S]                                                 = new Door[S] {}
  def mkDoor: Door[Closed.type]                                                      = apply[Closed.type]
  private def reverseState[S <: DoorState](d: Door[S]): Door[OppositeDoorState[d.S]] = apply[OppositeDoorState[d.S]]
  def open[S <: Closed.type](d: Door[S]): Door[OppositeDoorState[d.S]]               = reverseState(d)
  def closed[S <: Open.type](d: Door[S]): Door[OppositeDoorState[d.S]]               = reverseState(d)
  def knock[S <: DoorState](d: Door[S]): Door[d.S]                                   = apply[d.S]
