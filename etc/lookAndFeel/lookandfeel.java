public class LookAndFeel {

    public static void main(String[] args) {
      for ( LookAndFeelInfo info : UIManager.getInstalledLookAndFeels() ) {
        System.out.println( info.getName() );
      }
    }

}

