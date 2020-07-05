package Assignment_Sample
import cats.effect.{ExitCode, IO, IOApp}
import cats.implicits._
object Main extends IoApp{
def run(args:List[String])=
  Assignment_Server.stream[IO].compile.drain.as(ExitCode.Success)
}
